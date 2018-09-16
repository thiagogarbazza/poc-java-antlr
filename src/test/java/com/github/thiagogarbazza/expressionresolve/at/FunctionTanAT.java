package com.github.thiagogarbazza.expressionresolve.at;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(strict = true, plugin = {"pretty", "html:target/cucumber"}, tags = {"not @Ignore"},
  features = "classpath:com/github/thiagogarbazza/expressionresolve/at/FunctionTanAT.feature")
public class FunctionTanAT {
}
