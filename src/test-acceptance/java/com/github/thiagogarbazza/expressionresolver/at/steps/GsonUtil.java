package com.github.thiagogarbazza.expressionresolver.at.steps;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class GsonUtil {


  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");
  private static final Type LOCAL_DATE_TYPE = new TypeToken<LocalDate>() {}.getType();

  public static Gson gsonbuilder() {
    return new GsonBuilder()
      .registerTypeAdapter(LOCAL_DATE_TYPE, new JsonSerializer<LocalDate>() {
        @Override
        public JsonElement serialize(final LocalDate src, final Type typeOfSrc, final JsonSerializationContext context) {
          return new JsonPrimitive(src.format(FORMATTER));
        }
      })
      .create();
  }
}
