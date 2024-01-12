package org.opentdbtest.functional.regression;

import io.restassured.http.ContentType;
import org.opentdbtest.functional.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.is;

/**
 * This class contains regression tests for categories.
 * It extends the BaseTest class to inherit common test setup and teardown methods.
 */
public class CategoryTestsRegression extends BaseTest {

    /**
     * Test for retrieving questions with a specific category.
     * It sends a request with a category ID and verifies the response.
     */
    @Test(groups = "regression")
    public void retrievesQuestionsFromSpecifiedCategory() {
        int categoryId = 9;
        String categoryName = "General Knowledge";
        given()
                .param("amount", 5)
                .param("category", categoryId)
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("response_code", is(0))
                .body("results.size()", is(5))
                .body("results.category", everyItem(is(categoryName)));
    }

    /**
     * Test for handling invalid category ID.
     * It sends a request with an invalid category ID and verifies the response.
     */
    @Test(groups = "regression")
    public void handlesInvalidCategoryId() {
        String invalidCategoryId = "invalid";
        given()
                .param("amount", 5)
                .param("category", invalidCategoryId)
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("response_code", is(2));
    }

    /**
     * Test for handling non-existent category ID.
     * It sends a request with a non-existent category ID and verifies the response.
     */
    @Test(groups = "regression")
    public void handlesNonExistentCategoryId() {
        String nonExistentCategoryId = "12345";
        given()
                .param("amount", 5)
                .param("category", nonExistentCategoryId)
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("response_code", is(1));
    }

    /**
     * Test for handling empty category.
     * It sends a request with an empty category ID and verifies the response.
     */
    @Test(groups = "regression")
    public void handlesEmptyCategory() {
        String emptyCategoryId = "6";
        given()
                .param("category", emptyCategoryId)
                .param("amount", 5)
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("response_code", is(1))
                .body("results.size()", is(0));
    }

    /**
     * Test for handling special characters in category ID.
     * It sends a request with a category ID containing special characters and verifies the response.
     */
    @Test(groups = "regression")
    public void handlesSpecialCharactersInCategoryId() {
        String categoryWithSpecialChars = "2@#$@#";
        // Assuming the API supports special characters in category IDs
        given()
                .param("category", categoryWithSpecialChars)
                .param("amount", 5)
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("response_code", is(2));
    }

    /**
     * Test for retrieving questions from multiple categories.
     * It sends a request with multiple category IDs and verifies the response.
     */
    @Test(groups = "regression")
    public void retrievesQuestionsFromMultipleCategories() {
        String category1 = "9";
        String category2 = "10";
        given()
                .param("category", category1 + "," + category2)
                .param("amount", 5)
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("response_code", is(2));
    }
}