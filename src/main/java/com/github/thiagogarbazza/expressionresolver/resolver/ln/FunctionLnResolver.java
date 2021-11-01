package com.github.thiagogarbazza.expressionresolver.resolver.ln;

import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Inject;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Service;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.util.NumberUtil.toBigDecimal;
import static lombok.AccessLevel.PRIVATE;

@Service
@NoArgsConstructor(access = PRIVATE, onConstructor = @__(@Inject))
public class FunctionLnResolver {

  public BigDecimal resolver(final BigDecimal value) {
    final double ln = Math.log(value.doubleValue());

    return toBigDecimal(ln);
  }
}

