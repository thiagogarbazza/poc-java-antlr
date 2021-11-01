package com.github.thiagogarbazza.expressionresolver.at;

import io.cucumber.core.options.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("com/github/thiagogarbazza/expressionresolver/at/ExpressionAcceptanceTest.feature")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "com.github.thiagogarbazza.expressionresolver.at")
public class ExpressionAcceptanceTest {}
