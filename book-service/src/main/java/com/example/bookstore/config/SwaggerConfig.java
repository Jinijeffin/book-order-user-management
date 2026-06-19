package com.example.bookstore.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

@Bean
public OpenAPI bookStoreOpenAPI(){
return new OpenAPI()
   .addServersItem(new Server().url("https://scaling-doodle-5g4w59vjprwfv44g-8080.app.github.dev/"))
   .info(new Info()
   .title("BookStore API")
    .description("REST API for managing books in a bookstore microservice")
    .version("1.0.0")
   );
}
}