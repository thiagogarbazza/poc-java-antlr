package com.github.thiagogarbazza.expressionresolver.resolver.primitive;

import com.github.thiagogarbazza.expressionresolver.resolver.NormalizeResult;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ResolverPrimitiveNumber {

  private static ResolverPrimitiveNumber INSTANCE;

  public BigDecimal resolver(String value) {
    return NormalizeResult.toBigDecimal(value);
  }

  public static ResolverPrimitiveNumber getResolverPrimitiveNumber() {
    if (INSTANCE == null) {
      INSTANCE = new ResolverPrimitiveNumber();
    }

    return INSTANCE;
  }
}
