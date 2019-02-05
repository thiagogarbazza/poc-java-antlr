package com.github.thiagogarbazza.expressionresolver;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@ToString(of = {"value"})
@EqualsAndHashCode(of = {"value"})
public class Result {

  @Getter
  private final Object value;

  public Result(final Object value) {
    this.value = value;
  }

  public boolean asBoolean() {
    return (boolean) this.value;
  }

  public Collection<Boolean> asBooleans() {
    return (Collection<Boolean>) this.value;
  }

  public LocalDate asDate() {
    return (LocalDate) this.value;
  }

  public Collection<LocalDate> asDates() {
    return (Collection<LocalDate>) this.value;
  }

  public Map<String, Object> asMap() {
    return (Map<String, Object>) this.value;
  }

  public Collection<Map<String, Object>> asMaps() {
    return (Collection<Map<String, Object>>) this.value;
  }

  public BigDecimal asNumber() {
    return (BigDecimal) this.value;
  }

  public Collection<BigDecimal> asNumbers() {
    return (Collection<BigDecimal>) this.value;
  }

  public String asText() {
    return (String) this.value;
  }

  public Collection<String> asTexts() {
    return (Collection<String>) this.value;
  }

  public Collection<Map<String, Object>> toStandardized() {
    Collection<Map<String, Object>> result = new ArrayList<>();

    if (this.value != null) {
      if (isSingleValue()) {
        result.add(toMap(this.value));
      } else if (this.value instanceof Map) {
        result.add((Map<String, Object>) this.value);
      } else if (this.value instanceof Collection) {
        Collection collection = (Collection) this.value;
        if (collection.iterator().hasNext() && collection.iterator().next() instanceof Map) {
          result.addAll((Collection<? extends Map<String, Object>>) this.value);
        } else {
          for (Object data : collection) {
            result.add(toMap(data));
          }
        }
      }
    }

    return result;
  }

  private Map<String, Object> toMap(Object value) {
    Map<String, Object> map = new HashMap<>();
    map.put("value", value);
    return map;
  }

  public boolean isSingleValue() {
    return (this.value instanceof Boolean || this.value instanceof BigDecimal || this.value instanceof String || this.value instanceof LocalDate);
  }
}
