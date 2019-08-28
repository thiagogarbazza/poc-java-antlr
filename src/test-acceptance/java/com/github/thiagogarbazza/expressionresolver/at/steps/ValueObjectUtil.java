package com.github.thiagogarbazza.expressionresolver.at.steps;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;

@UtilityClass
class ValueObjectUtil {

  private static final String COLLECTION_SEPARATOR = ",";

  private static final Pattern IS_MAPA = Pattern.compile("^(\\{|\\[)");
  private static final Pattern IS_BOOLEAN = Pattern.compile("^(true|false)$");
  private static final Pattern IS_DATE = Pattern.compile("^(\\d{4}/\\d{1,2}/\\d{1,2})|(is today)$", CASE_INSENSITIVE);
  private static final Pattern IS_NUMBER = Pattern.compile("^(((\\+|-)?[\\d]*)|((\\+|-)?\\.[\\d]*)|((\\+|-)?[\\d]*\\.[\\d]*))$");


  static Collection<Object> stringToCollectionValueObject(final String expressionResult) {
    return Stream.of(trimToEmpty(expressionResult).split(COLLECTION_SEPARATOR))
      .map(StringUtils::trimToNull)
      .filter(StringUtils::isNotBlank)
      .map(ValueObjectUtil::stringToValueObject)
      .collect(Collectors.toList());
  }

  static Object stringToValueObject(final String value) {
    if(IS_MAPA.matcher(value).find()){
      return JsonUtil.stringToMap(value);
    } else if (IS_BOOLEAN.matcher(value).find()) {
      return BooleanUtils.toBoolean(value);
    } else if (IS_DATE.matcher(value).find()) {
      return UtilATDate.stringToDate(value);
    } else if (IS_NUMBER.matcher(value).find()) {
      return UtilATBigDecimal.stringToBigDecimal(value);
    }

    return value;
  }
}
