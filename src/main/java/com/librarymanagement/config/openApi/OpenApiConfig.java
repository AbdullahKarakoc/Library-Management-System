package com.librarymanagement.config.openApi;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Abdullah",
                        email = "abdullahkrkc1453@gmail.com",
                        url = "https://abdullahkrkc.com/blog"
                ),
                description = "OpenApi documentation for Library Management System",
                title = "Library Management",
                version = "1.0",
                license = @License(
                        name = "License name",
                        url = "https://license-url.com"
                ),
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "Prod ENV",
                        url = "https://abdullahkrkc.com/blog"
                )
        }
)
public class OpenApiConfig {
}
