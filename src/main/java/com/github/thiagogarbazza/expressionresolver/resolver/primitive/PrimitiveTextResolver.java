package com.github.thiagogarbazza.expressionresolver.resolver.primitive;

import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Inject;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Service;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Service
@NoArgsConstructor(access = PRIVATE, onConstructor_ = {@Inject})
public class PrimitiveTextResolver {

  private static final String END_QUOTES = "['\"]$";
  private static final String START_QUOTES = "^['\"]";

  public String resolver(final String value) {
    return value.replaceAll(START_QUOTES, EMPTY).replaceAll(END_QUOTES, EMPTY);
  }
}
