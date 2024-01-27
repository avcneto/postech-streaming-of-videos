package com.postechvideostreaming.videostreaming.config;

import static java.time.ZoneId.systemDefault;
import static java.time.ZonedDateTime.ofInstant;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

@Configuration
public class MongoConfig {

  @Bean
  public MongoCustomConversions customConversions() {
    List<Converter<?, ?>> converters = new ArrayList<>();
    converters.add(DateToZonedDateTimeConverter.INSTANCE);
    converters.add(ZonedDateTimeToDateConverter.INSTANCE);
    return new MongoCustomConversions(converters);
  }

  enum DateToZonedDateTimeConverter implements Converter<Date, ZonedDateTime> {

    INSTANCE;

    @Override
    public ZonedDateTime convert(Date date) {
      return ofInstant(date.toInstant(), systemDefault());
    }
  }

  enum ZonedDateTimeToDateConverter implements Converter<ZonedDateTime, Date> {

    INSTANCE;

    @Override
    public Date convert(ZonedDateTime zonedDateTime) {
      return Date.from(zonedDateTime.toInstant());
    }
  }
}
