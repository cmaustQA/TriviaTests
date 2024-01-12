package org.opentdbtest.functional.regression;

import io.restassured.http.ContentType;
import org.opentdbtest.functional.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.is;

/**
 * This class contains regression tests for difficulty levels.
 * It extends the BaseTest class to inherit common test setup and teardown methods.
 */
public class DifficultyTests extends BaseTest {

    /**
     * Test for retrieving questions with easy difficulty.
     * It sends a request with the difficulty level set to 'easy' and verifies the response.
     */
    @Test(groups = "regression")
    public void retrievesQuestionsWithEasyDifficulty() {
        String difficulty = "easy";
        given()
                .param("difficulty", difficulty)
                .param("amount", 5)
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("response_code", is(0))
                .body("results.size()", is(5))
                .body("results.difficulty", everyItem(is(difficulty)));
    }

    /**
     * Test for retrieving questions with medium difficulty.
     * It sends a request with the difficulty level set to 'medium' and verifies the response.
     */
    @Test(groups = "regression")
    public void retrievesQuestionsWithMediumDifficulty() {
        String difficulty = "medium";
        given()
                .param("difficulty", difficulty)
                .param("amount", 5)
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("response_code", is(0))
                .body("results.size()", is(5))
                .body("results.difficulty", everyItem(is(difficulty)));
    }

    /**
     * Test for retrieving questions with hard difficulty.
     * It sends a request with the difficulty level set to 'hard' and verifies the response.
     */
    @Test(groups = "regression")
    public void retrievesQuestionsWithHardDifficulty() {
        String difficulty = "hard";
        given()
                .param("difficulty", difficulty)
                .param("amount", 5)
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("response_code", is(0))
                .body("results.size()", is(5))
                .body("results.difficulty", everyItem(is(difficulty)));
    }

    /**
     * Test for handling invalid difficulty level.
     * It sends a request with an invalid difficulty level and verifies the response.
     */
    @Test(groups = "regression")
    public void handlesInvalidDifficultyLevel() {
        String invalidDifficulty = "invalid";
        given()
                .param("difficulty", invalidDifficulty)
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("response_code", is(2));
    }
}