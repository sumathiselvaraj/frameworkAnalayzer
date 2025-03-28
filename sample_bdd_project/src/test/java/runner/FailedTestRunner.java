
package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "@target/failed_scenarios.txt",
    glue = {"stepdefs"},
    plugin = {
        "pretty",
        "html:target/rerun-reports/cucumber-pretty.html",
        "json:target/rerun-reports/CucumberTestReport.json"
    },
    monochrome = true
)
public class FailedTestRunner {
    // This class will run only failed scenarios
}
