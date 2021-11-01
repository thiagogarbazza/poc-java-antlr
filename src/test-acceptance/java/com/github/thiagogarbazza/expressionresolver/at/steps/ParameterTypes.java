package com.github.thiagogarbazza.expressionresolver.at.steps;

import io.cucumber.java.ParameterType;

public class ParameterTypes {

  @ParameterType(name = "variable", value = "\\$[a-zA-Z][a-zA-Z0-9_]*")
  public String variable(String value) {
    return value;
  }
}
