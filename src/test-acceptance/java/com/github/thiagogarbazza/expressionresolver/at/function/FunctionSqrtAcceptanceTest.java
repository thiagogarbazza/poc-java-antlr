package com.github.thiagogarbazza.expressionresolver.at.function;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("com/github/thiagogarbazza/expressionresolver/at/function/FunctionSqrtAcceptanceTest.feature")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.github.thiagogarbazza.expressionresolver.at.steps")
public class FunctionSqrtAcceptanceTest {}
