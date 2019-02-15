package com.github.thiagogarbazza.expressionresolver.logging;

import lombok.extern.apachecommons.CommonsLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.time.Duration;
import java.time.LocalDateTime;

import static com.github.thiagogarbazza.expressionresolver.logging.AspectUtils.MSG_LOG_ENDING;
import static com.github.thiagogarbazza.expressionresolver.logging.AspectUtils.MSG_LOG_ERROR;
import static com.github.thiagogarbazza.expressionresolver.logging.AspectUtils.MSG_LOG_START;
import static com.github.thiagogarbazza.expressionresolver.logging.AspectUtils.toStringSignatureBuilder;
import static java.text.MessageFormat.format;

@Aspect
@CommonsLog
public class LogObserverResolver {

  private static final String POINTCUT = "execution(public * com.github.thiagogarbazza.expressionresolver.resolver..Resolver*.resolver(..))";

  @Around(POINTCUT)
  public Object logObserverResolver(ProceedingJoinPoint joinPoint) throws Throwable {
    LocalDateTime start = LocalDateTime.now();
    if (log.isTraceEnabled()) {
      log.trace(format(MSG_LOG_START, toStringSignatureBuilder(joinPoint)));
    }

    Object response;
    try {
      response = joinPoint.proceed();
    } catch (Throwable throwable) {
      log.error(format(MSG_LOG_ERROR, toStringSignatureBuilder(joinPoint), throwable.getMessage(), Duration.between(start, LocalDateTime.now())));
      throw throwable;
    }

    if (log.isTraceEnabled()) {
      log.trace(format(MSG_LOG_ENDING, toStringSignatureBuilder(joinPoint), response, Duration.between(start, LocalDateTime.now())));
    }

    return response;
  }
}
