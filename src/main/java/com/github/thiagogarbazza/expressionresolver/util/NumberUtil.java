package com.github.thiagogarbazza.expressionresolver.util;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@UtilityClass
public class NumberUtil {

  private static final int TAMANHO_CASAS_DECIMAIS = 34;
  private static final RoundingMode TIPO_ARREDONDAMENTO = RoundingMode.HALF_UP;
  public static final MathContext PRECISAO_E_ARREDONDAMENTO = new MathContext(TAMANHO_CASAS_DECIMAIS, TIPO_ARREDONDAMENTO);

  public static BigDecimal normalizeResultCompare(final int value) {
    if (value == 0) {
      return toBigDecimal(0);
    }

    return value < 0
      ? toBigDecimal(-1)
      : toBigDecimal(1);
  }

  public static BigDecimal setScale(final BigDecimal valor, final int casasDecimais) {
    return valor.setScale(casasDecimais, TIPO_ARREDONDAMENTO);
  }

  public static BigDecimal toBigDecimal(final String valor) {
    return toBigDecimal(new BigDecimal(valor));
  }

  public static BigDecimal toBigDecimal(final int value) {
    final BigDecimal result = BigDecimal.valueOf(value);

    return toBigDecimal(result);
  }

  public static BigDecimal toBigDecimal(final double value) {
    final BigDecimal result = BigDecimal.valueOf(value);

    return toBigDecimal(result);
  }

  public static BigDecimal toBigDecimal(final BigDecimal value) {
    return setScale(value, TAMANHO_CASAS_DECIMAIS);
  }
}

