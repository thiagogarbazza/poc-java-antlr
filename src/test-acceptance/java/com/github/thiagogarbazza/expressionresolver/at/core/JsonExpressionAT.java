package com.github.thiagogarbazza.expressionresolver.at.core;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.Ignore;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:com/github/thiagogarbazza/expressionresolver/at/core/JsonExpressionAT.feature",
  glue = {"classpath:com/github/thiagogarbazza/expressionresolver/at/"})
public class JsonExpressionAT {}
