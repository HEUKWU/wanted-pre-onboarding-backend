package spring.wantedpreonboardingbackend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI().info(new Info()
                .title("wanted-pre-onboarding-backend")
                .version("1.0")
                .description("프리 온보딩 백엔드 인턴십 선발과제"));
    }
}
