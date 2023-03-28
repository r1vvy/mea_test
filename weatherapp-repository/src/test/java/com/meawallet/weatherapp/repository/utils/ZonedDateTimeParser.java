package com.meawallet.weatherapp.repository.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class ZonedDateTimeParser {
    public static final ZonedDateTime ZONED_DATE_TIME = ZonedDateTime.of(LocalDateTime.parse("2023-03-28T06:00:00"), ZoneOffset.UTC);
}
