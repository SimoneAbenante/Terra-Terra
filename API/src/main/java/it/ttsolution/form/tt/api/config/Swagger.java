package it.ttsolution.form.tt.api.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger {
	
	@Bean
	PubNub pubnub() {

		PNConfiguration pnConf = new PNConfiguration();
		pnConf.setSubscribeKey("sub-c-aef1ccd4-8377-11e9-99de-d6d3b84c4a25");
		pnConf.setPublishKey("pub-c-a19b1d0f-ca7f-47fd-8f0e-a09206fa62f1");
		pnConf.setSecure(false);

		return new PubNub(pnConf);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"Terra Terra",
				"Terra-Terra API",
				"API 1.0",
				"User List",
				new Contact("User", "localhost:8085/", "Mail"),
				"License of API", "API license URL", Collections.emptyList());
	}
	
}
