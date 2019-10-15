package br.com.iagocolodetti.heroi;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author iagocolodetti
 */
@Configuration
@EnableSwagger2
@RestController
@RequestMapping({"/docs", "/swagger", "/api"})
public class SwaggerConfig {
    
    @Autowired
    private ServletContext servletContext;
    
    @GetMapping
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect(servletContext.getContextPath() + "/swagger-ui.html");
    }
    
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("br.com.iagocolodetti.heroi.controller"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo());
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("SpringHeroiREST API")
            .description("API para listar, adicionar e excluir heróis.")
            .version("1.0.1")
            .contact(new Contact("Iago Colodetti", "https://github.com/iagocolodetti", ""))
            .build();
    }
    
}
