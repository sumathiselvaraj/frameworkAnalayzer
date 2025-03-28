
package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.testng.annotations.Listeners;
import utils.RetryAnalyzer;

/**
 * TestRunner for running Cucumber BDD tests with retry mechanism
 */
@RunWith(Cucumber.class)
@CucumberOptions(
    features = "features",
    glue = {"stepdefs"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports/cucumber-pretty.html",
        "json:target/cucumber-reports/CucumberTestReport.json",
        "junit:target/cucumber-reports/cucumber-results.xml",
        "rerun:target/failed_scenarios.txt"
    },
    monochrome = true,
    dryRun = false
)
@Listeners(RetryAnalyzer.class)
public class TestRunner {
    // This class doesn't need any code as it's just a runner
}
