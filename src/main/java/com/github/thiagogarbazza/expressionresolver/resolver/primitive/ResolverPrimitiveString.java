package com.github.thiagogarbazza.expressionresolver.resolver.primitive;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@NoArgsConstructor(access = PRIVATE)
public class ResolverPrimitiveString {

  private static final String END_QUOTES = "['\"]$";
  private static final String START_QUOTES = "^['\"]";

  private static ResolverPrimitiveString INSTANCE;

  public String resolver(String value) {
    return value.replaceAll(START_QUOTES, EMPTY).replaceAll(END_QUOTES, EMPTY);
  }

  public static ResolverPrimitiveString getResolverPrimitiveString() {
    if (INSTANCE == null) {
      INSTANCE = new ResolverPrimitiveString();
    }

    return INSTANCE;
  }
}
