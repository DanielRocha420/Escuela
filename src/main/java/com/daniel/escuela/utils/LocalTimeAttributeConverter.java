package com.daniel.escuela.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Converter(autoApply = true)
public class LocalTimeAttributeConverter implements AttributeConverter<LocalTime, String> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("[HH:mm:ss][HH:mm]");

    @Override
    public String convertToDatabaseColumn(LocalTime attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.toString();
    }

    @Override
    public LocalTime convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().isEmpty()) {
            return null;
        }
        try {
            return LocalTime.parse(dbData.trim(), FORMATTER);
        } catch (DateTimeParseException e) {
            String[] partes = dbData.trim().split(":");
            int hora = Integer.parseInt(partes[0]);
            int minuto = Integer.parseInt(partes[1]);
            return LocalTime.of(hora, minuto);
        }
    }
}