package com.github.thiagogarbazza.expressionresolver.at.function;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:com/github/thiagogarbazza/expressionresolver/at/function/FunctionDateAT.feature",
  glue = {"classpath:com/github/thiagogarbazza/expressionresolver/at/steps/"})
public class FunctionDateAT {}
