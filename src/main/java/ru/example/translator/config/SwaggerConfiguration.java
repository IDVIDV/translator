package ru.example.translator.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI openApi() {
        Contact contact = new Contact()
                .email("ivakin_danya@mail.ru")
                .name("Ivakin Daniil");

        Info info = new Info()
                .title("Translator API")
                .version("1.0")
                .contact(contact)
                .description("API приложения-переводчика, использующего многопоточность и внешний сервис-переводчик");

        return new OpenAPI().info(info);
    }
}
