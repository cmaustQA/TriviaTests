package org.opentdbtest.functional.regression;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.opentdbtest.functional.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

/**
 * This class contains regression tests for the number of questions.
 * It extends the BaseTest class to inherit common test setup and teardown methods.
 */
public class NumberOfQuestionsTests extends BaseTest {

    /**
     * Test for retrieving the requested amount of questions.
     * It sends a request with a specified amount and verifies the response.
     */
    @Test(groups = "regression")
    public void retrievesRequestedAmountOfQuestions() {
        int requestedAmount = 5;
        Response response = given()
                .param("amount", requestedAmount)
                .when()
                .get("/");

        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("response_code", is(0))
                .body("results.size()", is(requestedAmount));
    }

    /**
     * Test for handling the maximum question limit.
     * It sends a request with an amount greater than the maximum limit and verifies the response.
     */
    @Test(groups = "regression")
    public void handlesMaximumQuestionLimit() {
        given()
                .param("amount", 55)
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("response_code", is(0))
                .body("results.size()", is(50));
    }

    /**
     * Test for handling an invalid amount of questions.
     * It sends a request with an invalid amount and verifies the response.
     */
    @Test(groups = "regression")
    public void handlesInvalidAmount() {
        int invalidAmount = -5;
        Response response = given()
                .param("amount", invalidAmount)
                .when()
                .get("/");

        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("response_code", is(2));
    }

    /**
     * Test for handling a zero amount of questions.
     * It sends a request with an amount of zero and verifies the response.
     */
    @Test(groups = "regression")
    public void handlesZeroAmount() {
        given()
                .param("amount", 0)
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("response_code", is(2));
    }

    /**
     * Test for handling an amount equal to the maximum limit.
     * It sends a request with an amount equal to the maximum limit and verifies the response.
     */
    @Test(groups = "regression")
    public void handlesAmountEqualToMaximumLimit() {
        given()
                .param("amount", 50)
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("response_code", is(0))
                .body("results.size()", is(50));
    }

    /**
     * Test for handling a floating point amount of questions.
     * It sends a request with a floating point amount and verifies the response.
     */
    @Test(groups = "regression")
    public void handlesFloatingPointAmount() {
        double floatingPointAmount = 2.5;
        given()
                .param("amount", floatingPointAmount)
                .when()
                .get("/")
                .then()
                .statusCode(500);
    }

    /**
     * Test for handling an extremely large amount of questions.
     * It sends a request with an extremely large amount and verifies the response.
     */
    @Test(groups = "regression")
    public void handlesExtremelyLargeAmount() {
        int extremelyLargeAmount = Integer.MAX_VALUE;
        given()
                .param("amount", extremelyLargeAmount)
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("response_code", is(0))
                .body("results.size()", is(50));
    }
}