package runners;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features",
        glue = "stepDefs",
        tags = "@newApplicant",
        dryRun = false,
        plugin = {"pretty", "html:target/XMLReports/Report.xml",
                "json:target/JSONReports/report.json",
                "html:target/HTMLReports/report.html",
                "timeline:target/cucumber",
                "json:target/cucumber.json"}
)
public class TestRunner {
}