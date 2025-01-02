package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        tags = "",
        features = "src/test/resources/features/ui",
        glue = {"ui/StepDefinitions"},
        plugin = {}
)
public class TestRunnerUI extends AbstractTestNGCucumberTests {
}
