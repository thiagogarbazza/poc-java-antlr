package com.github.thiagogarbazza.expressionresolver.at.function;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:com/github/thiagogarbazza/expressionresolver/at/function/FunctionCompareDateAT.feature",
  glue = {"classpath:com/github/thiagogarbazza/expressionresolver/at/steps/"})
public class FunctionCompareDateAT {}
