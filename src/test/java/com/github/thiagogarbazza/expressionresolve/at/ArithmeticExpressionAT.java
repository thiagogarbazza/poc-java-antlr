package com.github.thiagogarbazza.expressionresolve.at;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:com/github/thiagogarbazza/expressionresolve/at/ArithmeticExpressionAT.feature")
public class ArithmeticExpressionAT {}
