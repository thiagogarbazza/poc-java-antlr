package com.github.thiagogarbazza.expressionresolver.resolver.cos;

import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Inject;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Service;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.util.NumberUtil.toBigDecimal;
import static lombok.AccessLevel.PRIVATE;

@Service
@NoArgsConstructor(access = PRIVATE, onConstructor = @__(@Inject))
public class FunctionCosResolver {

  public BigDecimal resolver(final BigDecimal value) {
    final double cos = Math.cos(value.doubleValue());

    return toBigDecimal(cos);
  }
}
