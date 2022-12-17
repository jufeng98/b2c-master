package com.javamaster.b2c.cloud.test.boot.config;

import com.javamaster.b2c.cloud.test.boot.helper.LiteHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 当@Bean方法在没有使用@Configuration注解的类中声明时， 它们被称为以“lite”进行处理。 例如，
 * 用@Component修饰的类或者简单的类中都被认为是“lite”模式。
 * 不同于full @Configuration， lite @Bean 方法不能简单的在类内部定义依赖关系。 通常， 在“lite”模
 * 式下一个@Bean方法不应该调用其他的@Bean方法。
 * 只有在@Configuration注解的类中使用@Bean方法是确保使用“full”模式的推荐方法。 这也可以防止同样
 * 的@Bean方法被意外的调用很多次， 并有助于减少在’lite’模式下难以被追踪的细小bug。
 *
 * @author yudong
 * @date 2022/11/26
 */
@Component
public class LiteConfig {

    @Bean
    public LiteHelper liteHelper() {
        return new LiteHelper();
    }

}
