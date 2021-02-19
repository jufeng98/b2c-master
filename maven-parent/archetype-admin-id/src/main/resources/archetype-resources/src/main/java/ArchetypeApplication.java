package ${package}

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author maven-archetype
 */
@Slf4j
@EnableSwagger2
@EnableAspectJAutoProxy
@SpringCloudApplication
@EnableApolloConfig
@EnableTransactionManagement
@ComponentScan("${groupId}")
@MapperScan(value = "${groupId}.**.mapper")
public class ArchetypeApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(UntitledApplication.class, args);
        Environment environment = context.getEnvironment();
        log.info("swagger2 url:http://localhost:{}/swagger-ui.html#/", environment.getProperty("server.port"));
    }

}
