package com.librarymanagement.component;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Component
public class SwaggerAnnotations {

    public Operation createOperation(String description, String summary, ApiResponse... responses) {
        return new Operation() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return null;
            }

            @Override
            public String description() {
                return description;
            }

            @Override
            public RequestBody requestBody() {
                return null;
            }

            @Override
            public ExternalDocumentation externalDocs() {
                return null;
            }

            @Override
            public String summary() {
                return summary;
            }
            
            @Override
            public String operationId() {
                return "";
            }

            @Override
            public Parameter[] parameters() {
                return new Parameter[0];
            }

            @Override
            public String[] tags() {
                return new String[0];
            }

            @Override
            public boolean deprecated() {
                return false;
            }

            @Override
            public SecurityRequirement[] security() {
                return new SecurityRequirement[0];
            }

            @Override
            public ApiResponse[] responses() {
                return responses;
            }



            @Override
            public Server[] servers() {
                return new Server[0];
            }

            @Override
            public Extension[] extensions() {
                return new Extension[0];
            }


            @Override
            public boolean hidden() {
                return false;
            }

            @Override
            public boolean ignoreJsonView() {
                return false;
            }


            @Override
            public String method() {
                return "";
            }
        };
    }
}