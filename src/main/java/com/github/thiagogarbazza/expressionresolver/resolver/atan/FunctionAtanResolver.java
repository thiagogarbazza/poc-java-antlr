package com.github.thiagogarbazza.expressionresolver.resolver.atan;

import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Inject;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Service;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.util.NumberUtil.toBigDecimal;
import static lombok.AccessLevel.PRIVATE;

@Service
@NoArgsConstructor(access = PRIVATE, onConstructor_ = {@Inject})
public class FunctionAtanResolver {

  public BigDecimal resolver(final BigDecimal value) {
    final double atan = Math.atan(value.doubleValue());

    return toBigDecimal(atan);
  }
}

