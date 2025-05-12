package org.example;

import com.atlassian.oai.validator.restassured.OpenApiValidationFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.example.config.IPetBaseConfig;

import java.nio.charset.StandardCharsets;

public class RestSpec {
    public static final IPetBaseConfig petConfig = ConfigFactory.create(IPetBaseConfig.class);

    protected RequestSpecification reqSpec() {
        return new RequestSpecBuilder()
                .setConfig(RestAssuredConfig.config()
                        .httpClient(HttpClientConfig.httpClientConfig()
                                .setParam("http.connection.timeout", 60000)
                                .setParam("http.socket.timeout", 60000)
                        )
                        .objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory((cls, charset) ->
                                objectMapper()
                                        .findAndRegisterModules()
                                        .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
                                        .enable(SerializationFeature.WRITE_DATES_WITH_CONTEXT_TIME_ZONE)
                        ))
                )
                .addFilter(new AllureRestAssured())
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new OpenApiValidationFilter(petConfig.baseUrl() + "/openapi.json"))
                .setBaseUri(petConfig.baseUrl())
                .setContentType(ContentType.JSON.withCharset(StandardCharsets.UTF_8))
                .setAccept(ContentType.JSON)
                .build();
    }

    public static ObjectMapper objectMapper() {
        return new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
                .enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
    }
}
