package org.javamaster.b2c.swagger2;

import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ModelSpecificationBuilder;
import springfox.documentation.builders.PropertySpecificationBuilder;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;

import java.util.Date;

/**
 * @author yudong
 * @date 2021/6/16
 */
@Component
public class CustomerSwaggerProperty implements ModelPropertyBuilderPlugin {
    @Override
    public void apply(ModelPropertyContext modelPropertyContext) {
        if (!modelPropertyContext.getBeanPropertyDefinition().isPresent()) {
            return;
        }
        BeanPropertyDefinition beanPropertyDefinition = modelPropertyContext.getBeanPropertyDefinition().get();
        PropertySpecificationBuilder builder = modelPropertyContext.getSpecificationBuilder();
        if (beanPropertyDefinition.getRawPrimaryType() == Date.class) {

            builder.type(new ModelSpecificationBuilder().scalarModel(ScalarType.LONG).build());

        } else if (beanPropertyDefinition.getRawPrimaryType() == Long.class) {

            builder.type(new ModelSpecificationBuilder().scalarModel(ScalarType.STRING).build());

        }
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return delimiter == DocumentationType.SWAGGER_2;
    }
}
