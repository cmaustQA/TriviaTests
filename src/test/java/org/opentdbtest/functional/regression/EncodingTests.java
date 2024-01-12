package org.opentdbtest.functional.regression;

import io.restassured.http.ContentType;
import org.opentdbtest.functional.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * This class contains regression tests for encoding types.
 * It extends the BaseTest class to inherit common test setup and teardown methods.
 */
public class EncodingTests extends BaseTest {

    /**
     * Test for supporting legacy URL encoding.
     * It sends a request with the encoding type set to 'urlLegacy' and verifies the response.
     */
    @Test(groups = "regression")
    public void supportsLegacyUrlEncoding() {
        given()
                .param("amount", 5)
                .param("encode", "urlLegacy")
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("response_code", is(0))
                .body("results.question.find { it.contains('%3F') }", is(notNullValue()));
    }

    /**
     * Test for supporting RFC 3986 URL encoding.
     * It sends a request with the encoding type set to 'url3986' and verifies the response.
     */
    @Test(groups = "regression")
    public void supportsUrl3986Encoding() {
        given()
                .param("amount", 5)
                .param("encode", "url3986")
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("response_code", is(0))
                .body("results.question.find { it.contains('%3F') }", is(notNullValue()));
    }

    /**
     * Test for supporting Base64 encoding.
     * It sends a request with the encoding type set to 'base64' and verifies the response.
     */
    @Test(groups = "regression")
    public void supportsBase64Encoding() {
        given()
                .param("amount", 5)
                .param("encode", "base64")
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("response_code", is(0))
                .body("results.type.find { it.contains('bXVsdGlwbGU=') }", is(notNullValue()));
    }
}