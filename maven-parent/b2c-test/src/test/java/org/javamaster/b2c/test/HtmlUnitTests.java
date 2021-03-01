package org.javamaster.b2c.test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import lombok.extern.slf4j.Slf4j;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

/**
 * TODO
 *
 * @author yudong
 * @date 2021/2/26
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"env=test"})
public class HtmlUnitTests {
    @LocalServerPort
    private Integer port;
    @Autowired
    private WebApplicationContext context;
    private WebClient webClient;


    @Before
    public void setup() {
        webClient = MockMvcWebClientBuilder
                // demonstrates applying a MockMvcConfigurer (Spring Security)
                .webAppContextSetup(context, springSecurity())
                // for illustration only - defaults to ""
                .contextPath("")
                // By default MockMvc is used for localhost only;
                // the following will use MockMvc for example.com and example.org as well
                .useMockMvcForHosts("example.com", "example.org")
                .build();
    }

    @Test
    public void test() throws IOException {
        HtmlPage createMsgFormPage = webClient.getPage("http://localhost:" + port + "/user/form");
        HtmlForm form = createMsgFormPage.getHtmlElementById("messageForm");
        HtmlTextInput summaryInput = createMsgFormPage.getHtmlElementById("summary");
        summaryInput.setValueAttribute("Spring Rocks");
        HtmlTextArea textInput = createMsgFormPage.getHtmlElementById("text");
        textInput.setText("In case you didn't know, Spring Rocks!");
        HtmlSubmitInput submit = form.getOneHtmlElementByAttribute("input", "type", "submit");
        HtmlPage newMessagePage = submit.click();

        assertThat(newMessagePage.getUrl().toString()).endsWith("/messages/123");
        String id = newMessagePage.getHtmlElementById("id").getTextContent();
        assertThat(id).isEqualTo("123");
        String summary = newMessagePage.getHtmlElementById("summary").getTextContent();
        assertThat(summary).isEqualTo("Spring Rocks");
        String text = newMessagePage.getHtmlElementById("text").getTextContent();
        assertThat(text).isEqualTo("In case you didn't know, Spring Rocks!");
    }

}
