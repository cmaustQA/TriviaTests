package org.opentdbtest.functional.regression;

import io.restassured.http.ContentType;
import org.opentdbtest.functional.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

/**
 * This class contains regression tests for response codes.
 * It extends the BaseTest class to inherit common test setup and teardown methods.
 */
public class ResponseCodesTests extends BaseTest {

    /**
     * Test for successful retrieval of questions.
     * It sends a request for a specified amount of questions and verifies the response code is 0.
     */
    @Test(groups = "regression")
    public void canRetrieveQuestionsSuccessfully() {
        given()
                .param("amount", 5)
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("response_code", is(0));
    }

    /**
     * Test for handling no results.
     * It sends a request with a non-existent category and verifies the response code is 1.
     */
    @Test(groups = "regression")
    public void handlesNoResults() {
        int categoryId = 1000; // Assume a non-existent category
        given()
                .param("amount", 5)
                .param("category", categoryId)
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("response_code", is(1));
    }

    /**
     * Test for handling invalid parameters.
     * It sends a request with an invalid category and verifies the response code is 2.
     */
    @Test(groups = "regression")
    public void handlesInvalidAmountParameter() {
        given()
                .param("category", "invalid")
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("response_code", is(2));
    }

    /**
     * Test for handling invalid session tokens.
     * It sends a request with an invalid session token and verifies the response code is 3.
     */
    @Test(groups = "regression")
    public void handlesInvalidSessionToken() {
        String invalidToken = "invalid_token";
        given()
                .param("amount", 5)
                .param("token", invalidToken)
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("response_code", is(3));
    }

    /**
     * Test for handling rate limiting.
     * It sends two requests in quick succession and verifies the response code of the second request is 5.
     */
    @Test(groups = "regression")
    public void handlesRateLimiting() {
        // Trigger rate limit by making too many requests
        given()
                .param("amount", 5)
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("response_code", is(0));

        given()
                .param("amount", 5)
                .when()
                .get("/")
                .then()
                .statusCode(429)
                .contentType(ContentType.JSON)
                .body("response_code", is(5));
    }
}