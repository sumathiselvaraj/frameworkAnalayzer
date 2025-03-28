package runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
     features = "src/test/resources/features",
     glue = {"stepDefinitions", "hooks"},
    // tags = "@class",
     plugin = {"pretty", "html:target/cucumber-reports.html",
    		 "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
    		 "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
 )
public class TestRunner extends AbstractTestNGCucumberTests{


}


////
////		plugin= {"pretty","html:target/cucumber-reports/reports.html",
////				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
////				"json:target/cucumber-reports/cucumber.json",				
////				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
////				"rerun:target/rerun.txt"},
////				publish=true,
////				tags=""
//)
