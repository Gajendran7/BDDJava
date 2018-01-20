package Cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(
		format ={"pretty","json:target/Cucumber.json"},
		features = {"src/Cucumber/"}
		)

public class CucumberRunner {

}
