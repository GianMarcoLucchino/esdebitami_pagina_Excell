package it.esdebitamiretake.retake_ai;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
 
@Configuration
@EnableSwagger2
public class SpringConfiguration {
	@Bean
	public Docket api() {
		return

		new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.pccube.vvas.template.controller"))
				.paths(PathSelectors.any()).build();
	}
}


/* Location:              C:\Users\dercolano\Desktop\VVA\vas-template\vas-template-1.0.0.jar!\BOOT-INF\classes\it\isspa\vas\template\SpringConfiguration.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */