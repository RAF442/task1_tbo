package com.example.thymeleaf.validation;

import java.time.LocalDate;
import java.util.regex.Pattern;

/** Shared input rules for form DTOs and entity setters. HTML is not supported. */
public enum StudentFieldType {

    NAME(100, "(?=.*\\p{L})[\\p{L}\\p{M} '\u2019\\-]+", false,
            "Imię i nazwisko: 1–100 znaków; dozwolone litery, spacje, apostrofy i łączniki."),
    EMAIL(254, "[A-Za-z0-9_%+\\-]+(?:\\.[A-Za-z0-9_%+\\-]+)*@"
            + "[A-Za-z0-9](?:[A-Za-z0-9\\-]{0,61}[A-Za-z0-9])?"
            + "(?:\\.[A-Za-z0-9](?:[A-Za-z0-9\\-]{0,61}[A-Za-z0-9])?)+", false,
            "Podaj poprawny adres e-mail (maksymalnie 254 znaki, bez znaczników HTML)."),
    ZIP_CODE(9, "(?:[0-9]{2}-[0-9]{3}|[0-9]{5}-[0-9]{3})", false,
            "Kod pocztowy musi mieć format 00-001 lub 01001-000."),
    STREET(150, addressPattern(), false,
            "Ulica: 1–150 znaków; dozwolone litery, cyfry, spacje i znaki . , ' ’ / ( ) -"),
    NUMBER(20, "[\\p{L}\\p{N}][\\p{L}\\p{N}/ .\\-]*", false,
            "Numer budynku: 1–20 znaków; dozwolone litery, cyfry, spacje i znaki / . -"),
    COMPLEMENT(150, addressPattern(), true,
            "Uzupełnienie adresu: do 150 znaków; dozwolone litery, cyfry, spacje i znaki . , ' ’ / ( ) -"),
    DISTRICT(100, addressPattern(), false,
            "Dzielnica: 1–100 znaków; dozwolone litery, cyfry, spacje i znaki . , ' ’ / ( ) -"),
    CITY(100, addressPattern(), false,
            "Miasto: 1–100 znaków; dozwolone litery, cyfry, spacje i znaki . , ' ’ / ( ) -"),
    STATE(100, addressPattern(), false,
            "Region: 1–100 znaków; dozwolone litery, cyfry, spacje i znaki . , ' ’ / ( ) -");

    private final int maxLength;
    private final Pattern pattern;
    private final boolean optional;
    private final String message;

    StudentFieldType(int maxLength, String expression, boolean optional, String message) {
        this.maxLength = maxLength;
        this.pattern = Pattern.compile(expression);
        this.optional = optional;
        this.message = message;
    }

    private static String addressPattern() {
        return "(?=.*[\\p{L}\\p{N}])[\\p{L}\\p{M}\\p{N} .,'\u2019/()\\-]+";
    }

    public boolean isValid(String value) {
        if (value == null || value.isEmpty()) {
            return optional;
        }
        return value.length() <= maxLength
                && (this != EMAIL || value.indexOf('@') <= 64)
                && pattern.matcher(value).matches();
    }

    public String requireValid(String value) {
        if (!isValid(value)) {
            throw new IllegalArgumentException(message);
        }
        return value;
    }

    public String getMessage() {
        return message;
    }

    public static LocalDate requirePastBirthday(LocalDate birthday) {
        if (birthday == null || !birthday.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Data urodzenia jest wymagana i musi należeć do przeszłości.");
        }
        return birthday;
    }
}
