package org.opentdbtest.functional;

import io.restassured.RestAssured;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeGroups;

/**
 * This is a base test class that contains common setup and teardown methods for all tests.
 * It sets up the base URI for RestAssured and adds a delay after each test method.
 */
public class BaseTest {

    /**
     * This method is executed before all test methods in the class.
     * It sets up the base URI for RestAssured.
     */
    @BeforeGroups(groups = {"exhaustive", "regression"})
    public static void setup() {
        RestAssured.baseURI = "https://opentdb.com/api.php";
    }

    /**
     * This method is executed after each test method.
     * It adds a delay of 5 seconds between each test to avoid hitting rate limits.
     *
     * @throws InterruptedException if the thread is interrupted while sleeping
     */
    @AfterMethod(groups = {"exhaustive", "regression"})
    public void delay() throws InterruptedException {
        Thread.sleep(5000);
    }
}