package com.example.sistemadegimnasio.models;

import androidx.room.TypeConverter;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class Converters {
    @TypeConverter
    // Método para convertir de Long a LocalDate
    public static LocalDate fromTimestamp(Long value) {
        return value == null ? null : LocalDate.ofEpochDay(value);
    }

    // Método para convertir de LocalDate a Long
    @TypeConverter
    public static Long dateToTimestamp(LocalDate date) {
        return date == null ? null : date.toEpochDay();
    }

    @TypeConverter
    public static BigDecimal toBigDecimal(String value) {
        return value == null ? null : new BigDecimal(value);
    }

    @TypeConverter
    public static String fromBigDecimal(BigDecimal value) {
        return value == null ? null : value.toString();
    }
}
