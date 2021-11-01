package com.github.thiagogarbazza.expressionresolver.resolver.comparestring;

import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Inject;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Service;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.util.NumberUtil.normalizeResultCompare;
import static lombok.AccessLevel.PRIVATE;

@Service
@NoArgsConstructor(access = PRIVATE, onConstructor = @__(@Inject))
public class FunctionCompareStringResolver {

  public BigDecimal resolver(final String left, final String right) {
    final int result = left.compareTo(right);

    return normalizeResultCompare(result);
  }
}
