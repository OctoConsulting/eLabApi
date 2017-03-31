package com.octo.elab.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.google.common.base.Strings;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.AbstractPathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Autowired
	Environment environment;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("gov.gsa.sam.controller"))
				// .paths(PathSelectors.regex(environment.getRequiredProperty("iae.fbo.api.version")
				// + "/.*"))
				.paths(PathSelectors.regex("/rms.*")).build().pathProvider(new ApplicationPathProvider())
				.host(environment.getProperty("iae.api.umbrella.host")).securitySchemes(getSecuritySchemes())
				.protocols(getProtocols()).produces(getFormat()).consumes(getFormat()).useDefaultResponseMessages(false)
				.globalOperationParameters(getGlobalParameters()).apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		Contact contact = null;
		return new ApiInfo("Role Management API", "", "0.1.0", "", contact, "GSA", "");
	}

	private Set<String> getProtocols() {
		Set<String> p = new HashSet<>();
		p.add(environment.getProperty("iae.api.umbrella.host.protocol", "http"));

		return p;
	}

	private Set<String> getFormat() {
		Set<String> p = new HashSet<>();
		p.add("application/json");

		return p;
	}

	private List<SecurityScheme> getSecuritySchemes() {
		List<SecurityScheme> list = new ArrayList<>();
		list.add(new ApiKey("api_key", "api_key", "query"));
		return list;
	}

	private List<Parameter> getGlobalParameters() {
		List<Parameter> list = new ArrayList<>();
		list.add(new ParameterBuilder().name("api_key").description("API KEY").modelRef(new ModelRef("string"))
				.parameterType("query").required(true).build());

		return list;
	}

	public class ApplicationPathProvider extends AbstractPathProvider {
		public static final String ROOT = "/";

		@Override
		protected String applicationPath() {
			String path = environment.getProperty("iae.api.path.prefix");
			return Strings.isNullOrEmpty(path) ? ROOT : path;
		}

		@Override
		protected String getDocumentationPath() {
			return ROOT;
		}
	}

}