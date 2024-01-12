package org.opentdbtest.util;

import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * This class implements the ITestListener interface and overrides its methods to provide custom behavior for test events.
 */
public class TestListener implements ITestListener {

    /**
     * This method is called when a test starts.
     * @param result The result of the test.
     */
    @Override
    public void onTestStart(ITestResult result) {
        printResult("Test started", result);
    }

    /**
     * This method is called when a test passes.
     * @param result The result of the test.
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        printResult("Test passed", result);
    }

    /**
     * This method is called when a test fails.
     * @param result The result of the test.
     */
    @Override
    public void onTestFailure(ITestResult result) {
        printResult("Test failed", result);
    }

    /**
     * This method prints the result of a test.
     * @param message The message to be printed.
     * @param result The result of the test.
     */
    private void printResult(String message, ITestResult result) {
        Object[] parameters = result.getParameters();
        message += ": " + result.getName();
        if (parameters.length > 0) {
            message += ", Category ID: " + parameters[0];
        }
        System.out.println(message);
    }
}