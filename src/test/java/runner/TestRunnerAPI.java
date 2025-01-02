package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        tags = "",
        features = "src/test/resources/features/api",
        glue = {"api/StepDefinitions"},
        plugin = {}
)
public class TestRunnerAPI extends AbstractTestNGCucumberTests {
}
