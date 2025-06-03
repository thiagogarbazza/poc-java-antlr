package com.github.thiagogarbazza.expressionresolver.resolver.primitive;

import com.github.thiagogarbazza.expressionresolver.util.NumberUtil;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Inject;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Service;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@Service
@NoArgsConstructor(access = PRIVATE, onConstructor_ = {@Inject})
public class PrimitiveNumberResolver {

  public BigDecimal resolver(final String value) {
    return NumberUtil.toBigDecimal(value);
  }
}
