package com.github.thiagogarbazza.expressionresolver.resolver.sin;

import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Inject;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Service;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.util.NumberUtil.toBigDecimal;
import static lombok.AccessLevel.PRIVATE;

@Service
@NoArgsConstructor(access = PRIVATE, onConstructor_ = {@Inject})
public class FunctionSinResolver {

  public BigDecimal resolver(final BigDecimal value) {
    final double sin = Math.sin(value.doubleValue());

    return toBigDecimal(sin);
  }
}
