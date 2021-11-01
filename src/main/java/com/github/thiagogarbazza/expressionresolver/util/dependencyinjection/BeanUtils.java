package com.github.thiagogarbazza.expressionresolver.util.dependencyinjection;

import lombok.experimental.UtilityClass;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.stream.Stream;

import static java.text.MessageFormat.format;

@UtilityClass
class BeanUtils {

  public static <T> void checkBeanAnnotated(final Class<T> bean) {
    final Annotation[] annotations = bean.getAnnotations();

    if (!Stream.of(annotations).anyMatch(a -> a instanceof Service)) {
      throw new RuntimeException(format("Bean \"{0}\" is not annotated for DI.", bean.getCanonicalName()));
    }
  }

  public static <T> Constructor<T> getConstructor(final Class<T> bean) {
    return (Constructor<T>) Stream.of(bean.getDeclaredConstructors())
      .filter(constructor -> constructor.isAnnotationPresent(Inject.class))
      .findFirst()
      .orElseThrow(() -> new RuntimeException(format("Bean \"{0}\" there is no annotated constructor to be used for DI.", bean.getCanonicalName())));
  }

  public static <T> T newInstance(final Constructor<T> constructor, final Object[] parameters) {
    try {
      constructor.setAccessible(true);
      return constructor.newInstance(parameters);
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }
}
