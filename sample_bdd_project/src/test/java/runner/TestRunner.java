package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "features",
    glue = {"stepdefs"},
    plugin = {"pretty", "html:target/cucumber-reports", "json:target/cucumber.json"},
    tags = "@smoke or @regression",
    monochrome = true
)
public class TestRunner {
    // This is a test runner class that executes the BDD tests
    // It uses JUnit to run the tests and Cucumber for BDD implementation
}