package org.opentdbtest.functional.regression;

import io.restassured.http.ContentType;
import org.opentdbtest.functional.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.is;

/**
 * This class contains regression tests for question types.
 * It extends the BaseTest class to inherit common test setup and teardown methods.
 */
public class QuestionTypeTests extends BaseTest {

    /**
     * Test for retrieving questions of type 'multiple choice'.
     * It sends a request with the question type set to 'multiple' and verifies the response.
     */
    @Test(groups = "regression")
    public void retrieveMultipleChoiceType() {
        String questionType = "multiple";
        given()
                .param("type", questionType)
                .param("amount", 5)
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("response_code", is(0))
                .body("results.size()", is(5))
                // Assert all retrieved questions have the specified type
                .body("results.type", everyItem(is(questionType)));
    }

    /**
     * Test for retrieving questions of type 'true or false'.
     * It sends a request with the question type set to 'boolean' and verifies the response.
     */
    @Test(groups = "regression")
    public void retrieveTrueFalseType() {
        String questionType = "boolean";
        given()
                .param("type", questionType)
                .param("amount", 5)
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("response_code", is(0))
                .body("results.size()", is(5))
                // Assert all retrieved questions have the specified type
                .body("results.type", everyItem(is(questionType)));
    }

    /**
     * Test for handling an invalid question type.
     * It sends a request with an invalid question type and verifies the response.
     */
    @Test(groups = "regression")
    public void handlesInvalidQuestionType() {
        String invalidQuestionType = "invalid";
        given()
                .param("type", invalidQuestionType)
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("response_code", is(2));
    }
}