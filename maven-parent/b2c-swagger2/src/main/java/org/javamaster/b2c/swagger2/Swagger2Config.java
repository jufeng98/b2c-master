package org.javamaster.b2c.swagger2;

import com.google.common.collect.Sets;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yudong
 * @date 2020/10/21
 */
@Configuration
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        List<RequestParameter> params = new ArrayList<>();
        RequestParameter param = new RequestParameterBuilder()
                .name("Authorization")
                .description("访问授权码头字段")
                .required(true)
                .in(ParameterType.HEADER)
                .build();
        params.add(param);

        return new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .select()
                // 方法需要有ApiOperation注解才能生成接口文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 路径使用any风格
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(params)
                .consumes(Sets.newHashSet(MediaType.APPLICATION_JSON_VALUE))
                .produces(Sets.newHashSet(MediaType.APPLICATION_JSON_VALUE))
                // 接口文档的基本信息
                .apiInfo(apiInfo());
    }

    @SneakyThrows
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("业务中台")
                .description("描述")
                .version("1.0.0")
                .build();
    }
}
