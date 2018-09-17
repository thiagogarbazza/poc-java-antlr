package com.github.thiagogarbazza.expressionresolve.at;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.Ignore;
import org.junit.runner.RunWith;

@Ignore
@RunWith(Cucumber.class)
@CucumberOptions(strict = true, plugin = {"pretty", "html:target/cucumber"}, tags = {"not @Ignore"},
  features = "classpath:com/github/thiagogarbazza/expressionresolve/at/FunctionTodayAT.feature")
public class FunctionTodayAT {
}
