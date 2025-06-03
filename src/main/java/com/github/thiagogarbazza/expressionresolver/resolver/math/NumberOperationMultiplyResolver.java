package com.github.thiagogarbazza.expressionresolver.resolver.math;

import com.github.thiagogarbazza.expressionresolver.util.NumberUtil;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Inject;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Service;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@Service
@NoArgsConstructor(access = PRIVATE, onConstructor_ = {@Inject})
public class NumberOperationMultiplyResolver {

  public BigDecimal resolver(final BigDecimal left, final BigDecimal right) {
    final BigDecimal result = left.multiply(right);

    return NumberUtil.toBigDecimal(result);
  }
}
