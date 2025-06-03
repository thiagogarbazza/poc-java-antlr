package com.github.thiagogarbazza.expressionresolver.resolver.asin;

import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Inject;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Service;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.util.NumberUtil.toBigDecimal;
import static lombok.AccessLevel.PRIVATE;

@Service
@NoArgsConstructor(access = PRIVATE, onConstructor_ = {@Inject})
public class FunctionAsinResolver {

  public BigDecimal resolver(final BigDecimal value) {
    final double asin = Math.asin(value.doubleValue());

    return toBigDecimal(asin);
  }
}
