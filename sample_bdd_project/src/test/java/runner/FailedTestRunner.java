
package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

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
public class FailedTestRunner extends AbstractTestNGCucumberTests {
}
