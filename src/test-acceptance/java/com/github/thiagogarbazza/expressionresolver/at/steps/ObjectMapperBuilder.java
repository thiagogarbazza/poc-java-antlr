package com.github.thiagogarbazza.expressionresolver.at.steps;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonTokenId;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static com.github.thiagogarbazza.expressionresolver.at.steps.UtilATBigDecimal.stringToBigDecimal;

@UtilityClass
class ObjectMapperBuilder {

  public static ObjectMapper build(){
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());

//    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
//    mapper.setDateFormat(df);

    SimpleModule module = new SimpleModule();
    module.addDeserializer(Object.class, new LocalDateDeserializer());
    module.addDeserializer(Number.class, new NumberDeserializer());
    module.addDeserializer(Boolean.class, new BooleanDeserializer());
    mapper.registerModule(module);

    return mapper;
  }

  public static class LocalDateDeserializer extends UntypedObjectDeserializer {

    @Override
    public Object deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException {
      if (jp.getCurrentTokenId() == JsonTokenId.ID_STRING) {
        return ValueObjectUtil.stringToValueObject(jp.getText());
      }

      return super.deserialize(jp, ctxt);
    }
  }

  public static class NumberDeserializer extends JsonDeserializer<Number> {

    @Override
    public Number deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
      return stringToBigDecimal(p.getText());
    }
  }

  public static class BooleanDeserializer extends JsonDeserializer<Boolean> {

    @Override
    public Boolean deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
      return BooleanUtils.toBoolean(p.getText());
    }
  }
}
