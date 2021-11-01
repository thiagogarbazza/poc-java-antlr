package com.github.thiagogarbazza.expressionresolver.at.core;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("com/github/thiagogarbazza/expressionresolver/at/core/VariablesAcceptanceTest.feature")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.github.thiagogarbazza.expressionresolver.at.steps")
public class VariablesAcceptanceTest {}
