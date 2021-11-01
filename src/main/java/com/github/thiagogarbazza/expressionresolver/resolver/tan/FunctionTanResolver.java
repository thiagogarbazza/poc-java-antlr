package com.github.thiagogarbazza.expressionresolver.resolver.tan;

import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Inject;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Service;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.util.NumberUtil.toBigDecimal;
import static lombok.AccessLevel.PRIVATE;

@Service
@NoArgsConstructor(access = PRIVATE, onConstructor = @__(@Inject))
public class FunctionTanResolver {

  public BigDecimal resolver(final BigDecimal value) {
    final double tan = Math.tan(value.doubleValue());

    return toBigDecimal(tan);
  }
}

