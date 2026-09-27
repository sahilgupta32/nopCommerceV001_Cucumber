package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions
        (
        features = {".//Features/Customers.feature", ".//Features/Login.feature"},
        glue = "stepDefinitions",
        dryRun = false,
        monochrome = true,
        plugin = {"pretty", "html:Reports/report.html"},
        tags= "@sanity or @regression"
        )

public class TestRunner {
}
