package com.github.thiagogarbazza.expressionresolver.util.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonTokenId;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import static com.github.thiagogarbazza.expressionresolver.util.NumberUtil.toBigDecimal;
import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;

@UtilityClass
class ObjectMapperBuilder {

  public static ObjectMapper build() {
    final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    final SimpleModule module = new SimpleModule()
      .addDeserializer(Object.class, new LocalDateDeserializer())
      .addDeserializer(Number.class, new NumberDeserializer());

    return new ObjectMapper()
      .registerModule(new JavaTimeModule())
      .registerModule(module)
      .setDateFormat(dateFormat);
  }

  public static class LocalDateDeserializer extends UntypedObjectDeserializer {

    private static final Pattern IS_DATE = Pattern.compile("^(\\d{4}-\\d{1,2}-\\d{1,2})$", CASE_INSENSITIVE);
    private static final Pattern IS_DATE_TIME = Pattern.compile("^(\\d{4}-\\d{1,2}-\\d{1,2}T\\d{1,2}:\\d{1,2}:\\d{1,2}.\\d{1,3}(Z)?)$", CASE_INSENSITIVE);

    @Override
    public Object deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException {
      if (jp.getCurrentTokenId() == JsonTokenId.ID_STRING) {
        return converter(jp.getText());
      }

      return super.deserialize(jp, ctxt);
    }

    private Object converter(final String valor) {
      if (IS_DATE.matcher(valor).find()) {
        return LocalDate.parse(valor, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
      } else if (IS_DATE_TIME.matcher(valor).find()) {
        final String dateTime = valor.replace("T", SPACE).replace("Z", EMPTY);
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
      }

      return valor;
    }
  }

  public static class NumberDeserializer extends JsonDeserializer<Number> {

    @Override
    public Number deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      return toBigDecimal(p.getText());
    }
  }
}
