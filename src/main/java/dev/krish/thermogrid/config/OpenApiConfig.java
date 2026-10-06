package dev.krish.thermogrid.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Title and summary shown at the top of /swagger-ui.html. */
@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI thermoGridOpenApi() {
        return new OpenAPI().info(new Info()
            .title("ThermoGrid API")
            .version("v1")
            .description("""
                Read-only analysis of which regions run out of power under projected heatwaves.
                Figures are illustrative, not real utility or climate data.""")
            .license(new License().name("Source on GitHub").url("https://github.com/KoolAidKrish/ThermoGrid")));
    }
}
