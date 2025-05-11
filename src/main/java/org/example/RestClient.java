package org.example;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestClient extends RestSpec {

    public ExtractableResponse<Response> get(
            String path,
            int statusCode) {
        return given().spec(reqSpec())
                .when()
                .get(path)
                .then()
                .assertThat()
                .statusCode(statusCode)
                .extract();

    }

    public <T> T post(
            Object body,
            String endpoint,
            int statusCode,
            Class<T> tClass) {
        return given().spec(reqSpec())
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .assertThat()
                .statusCode(statusCode)
                .extract().response()
                .as(tClass);
    }

}
