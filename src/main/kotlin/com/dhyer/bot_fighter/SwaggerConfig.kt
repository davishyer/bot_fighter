package com.dhyer.bot_fighter

import com.dhyer.bot_fighter.annotations.Private
import com.google.common.base.Predicates
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {
  @Bean
  fun api(): Docket = Docket(DocumentationType.SWAGGER_2)
                        .apiInfo(apiInfo())
                        .useDefaultResponseMessages(false)
                        .select()
                        .apis(Predicates.not(RequestHandlerSelectors.withMethodAnnotation(Private::class.java)))
                        .paths(Predicates.not(PathSelectors.regex("/(error|health-check).*")))
                        .build()

  fun apiInfo(): ApiInfo {
    return ApiInfoBuilder()
            .title("Bot-Fighter API")
            .description("for questions, ping @dhyer in slack")
            .build()
  }
}