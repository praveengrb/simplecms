package s.praveen.cms;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The type Cms application.
 */
@SpringBootApplication
@EnableJpaRepositories
@OpenAPIDefinition(info = @Info(title = "CMS API", version = "1.0", description = "CMS Application API"))
public class CmsApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }

}
