package com.github.thiagogarbazza.expressionresolver.resolver.primitive;

import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Inject;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Service;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.github.thiagogarbazza.expressionresolver.util.LocalDateUtil.toLocalDate;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Service
@NoArgsConstructor(access = PRIVATE, onConstructor_ = {@Inject})
public class PrimitiveDateResolver {

  private static final String END_QUOTES = "['\"]$";
  private static final Pattern PATTERN_MONTH = Pattern.compile("(?<month>[A-Za-z]{3})");
  private static final String START_QUOTES = "^['\"]";

  public LocalDate resolver(final String value) {
    final String date = Month.replaceTextMonth(value).replaceAll(START_QUOTES, EMPTY).replaceAll(END_QUOTES, EMPTY);

    return toLocalDate(date);
  }

  @Getter(value = PRIVATE)
  @RequiredArgsConstructor(access = PRIVATE)
  private enum Month {
    JAN("01"),
    FEB("02"),
    MAR("03"),
    APR("04"),
    MAY("05"),
    JUN("06"),
    JUL("07"),
    AUG("08"),
    SEP("09"),
    OCT("10"),
    NOV("11"),
    DEC("12");

    private final String id;

    public static String replaceTextMonth(final String value) {
      final Matcher matcher = PATTERN_MONTH.matcher(value);

      if (matcher.find()) {
        final String abbr = matcher.group("month");
        final Month month = Month.valueOf(abbr.toUpperCase());

        return matcher.replaceAll(String.valueOf(month.getId()));
      }

      return value;
    }
  }
}
