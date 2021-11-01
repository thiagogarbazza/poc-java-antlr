package com.github.thiagogarbazza.expressionresolver.util.dependencyinjection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BeanUtilsTest {

  @Test
  void verifyErrorOrNewInstance() throws InvocationTargetException, InstantiationException, IllegalAccessException {
    final Constructor<?> constructor = Arrays.stream(BeanUtils.class.getDeclaredConstructors()).findFirst().get();
    final Object[] parameters = new Object[] {};

    final RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {BeanUtils.newInstance(constructor, parameters);});

    assertNotNull(runtimeException);
  }
}