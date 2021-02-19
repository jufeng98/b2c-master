package org.javamaster.b2c.test.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class MyJsonTests {

    @Autowired
    private JacksonTester<SecurityProperties.User> json;

    @Test
    public void testSerialize() throws Exception {
        SecurityProperties.User details = new SecurityProperties.User();
        details.setName("liangyudong");
        // Assert against a `.json` file in the same package as the test
        assertThat(this.json.write(details)).isEqualToJson("{\"name\":\"liangyudong\"}");
        // Or use JSON path based assertions
        assertThat(this.json.write(details)).hasJsonPathStringValue("@.name");
        assertThat(this.json.write(details)).extractingJsonPathStringValue("@.name")
                .isEqualTo("liangyudong");
    }

    @Test
    public void testDeserialize() throws Exception {
        SecurityProperties.User details = new SecurityProperties.User();
        details.setName("liangyudong");
        String content = "{\"name\":\"liangyudong\"}";
        assertThat(this.json.parseObject(content).getName()).isEqualTo("liangyudong");
    }

}