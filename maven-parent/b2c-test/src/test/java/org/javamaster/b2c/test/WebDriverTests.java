package org.javamaster.b2c.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

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
public class WebDriverTests {
    // @Autowired
    // private WebApplicationContext applicationContext;
    @LocalServerPort
    private Integer port;
    private WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/yu/AppData/Local/Google/Chrome/Application/chromedriver.exe");

        // driver = MockMvcHtmlUnitDriverBuilder
        //         // demonstrates applying a MockMvcConfigurer (Spring Security)
        //         .webAppContextSetup(applicationContext)
        //         // for illustration only - defaults to ""
        //         .contextPath("")
        //         // By default MockMvc is used for localhost only;
        //         // the following will use MockMvc for example.com and example.org as well
        //         .useMockMvcForHosts("example.com")
        //         .build();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void close() {
        driver.quit();
    }

    @Test
    public void exampleTest() {
        String baseUrl = "http://localhost:" + port;
        driver.get(baseUrl);
        driver.findElement(By.tagName("input")).sendKeys("BOOK TITLE");
        driver.findElement(By.tagName("form")).submit();
        WebElement dl = driver.findElement(By.cssSelector("dt.bookHeadline"));
        assertEquals("BOOK TITLE by BOOK AUTHOR (ISBN: 1234567890)", dl.getText());
        WebElement dt = driver.findElement(By.cssSelector("dd.bookDescription"));
        assertEquals("DESCRIPTION", dt.getText());
    }

}
