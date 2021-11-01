package com.github.thiagogarbazza.expressionresolver.resolver.comparenumber;

import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Inject;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Service;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.util.NumberUtil.normalizeResultCompare;
import static lombok.AccessLevel.PRIVATE;

@Service
@NoArgsConstructor(access = PRIVATE, onConstructor = @__(@Inject))
public class FunctionCompareNumberResolver {

  public BigDecimal resolver(final BigDecimal left, final BigDecimal right) {
    final int result = left.compareTo(right);

    return normalizeResultCompare(result);
  }
}
