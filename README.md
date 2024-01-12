# Trivia Tests
This is a sample test suite for the Trivia API at https://opentdb.com/api.php.
It contains a regression test suite as well as a more exhaustive test for a particular functionality of the API.
There is also a Jmeter plugin that can be used for performance testing.


## Dependencies
The project uses several dependencies, including:  
 - TestNG for testing framework.
 - Rest-assured for REST API Testing.
 - Log4j for logging.
 - Apache Commons CSV for working with CSV files.


## Building and Running Tests
The project uses Maven, so you can build and run the tests using the Maven command line tool. 
The pom.xml file includes a configuration for the Maven Surefire Plugin to run the tests.  To build the project and run the tests, use the following command:

```bash
mvn clean test
```

To run only the regression tests, use the following command:

```bash
mvn clean test -Dgroups=regression
```

To run only the exhaustive test, use the following command:

```bash
mvn clean test -Dgroups=exhaustive
```

To run only the performance tests, use the following command:

```bash
mvn verify -Pjmeter-tests
```

_Note: to prevent throttling, there is a 5-second delay between each request._

Results for exhaustive / regression tests are written to the target/surefire-reports directory.
Results for performance tests are written to the target/jmeter/results directory.
