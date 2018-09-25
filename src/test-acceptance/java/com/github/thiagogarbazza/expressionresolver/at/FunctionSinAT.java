package com.github.thiagogarbazza.expressionresolver.at;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:com/github/thiagogarbazza/expressionresolver/at/FunctionSinAT.feature")
public class FunctionSinAT {}
