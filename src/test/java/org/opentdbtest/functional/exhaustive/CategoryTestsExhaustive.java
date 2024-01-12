package org.opentdbtest.functional.exhaustive;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.opentdbtest.functional.BaseTest;
import org.opentdbtest.util.PropertyReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * This class contains exhaustive tests for all question categories.
 * It extends the BaseTest class to inherit common test setup and teardown methods.
 */
public class CategoryTestsExhaustive extends BaseTest {

    // PropertyReader instance to read properties from a properties file
    private final PropertyReader propertyReader = new PropertyReader();

    /**
     * DataProvider method to provide category data for the test.
     * It reads the CSV file specified in the properties file and returns category IDs and names.
     *
     * @return 2D array containing category IDs and names
     */
    @DataProvider(name = "categoriesProvider")
    public Object[][] getCategoriesFromCSV() {
        String filePath = propertyReader.getPropertyValue("CSV_FILE_PATH");
        List<String[]> categories = readCategoriesFromCSV(filePath);

        return categories.stream().map(category -> new Object[]{category[0], category[1]}).toArray(Object[][]::new);
    }

    /**
     * Test method to test each category.
     * It sends a request with the category ID and verifies the category name in the response.
     *
     * @param categoryId   the ID of the category to test
     * @param categoryName the name of the category to verify in the response
     */
    @Test(groups = "exhaustive", dataProvider = "categoriesProvider")
    public void testCategory(String categoryId, String categoryName) {
        String response = given()
                .param("amount", 5)
                .param("category", categoryId)
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

    }

    /**
     * Helper method to read categories from a CSV file.
     * It reads the CSV file and returns a list of category IDs and names.
     *
     * @param filePath the path of the CSV file
     * @return a list of category IDs and names
     */
    private List<String[]> readCategoriesFromCSV(String filePath) {
        List<String[]> categories = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {
            for (CSVRecord record : csvParser) {
                String id = record.get(0);
                String name = record.get(1);
                categories.add(new String[]{id, name});
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read categories from CSV file: " + filePath, e);
        }
        return categories;
    }
}