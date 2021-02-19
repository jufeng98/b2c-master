package org.javamaster.spring.env.props;

import lombok.Data;
import org.javamaster.spring.env.model.Person;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yudong
 * @date 2020/7/31
 */
@Data
@Component
@ConfigurationProperties("foo")
public class PersonProperties {

    private final List<Person> list = new ArrayList<>();

}
