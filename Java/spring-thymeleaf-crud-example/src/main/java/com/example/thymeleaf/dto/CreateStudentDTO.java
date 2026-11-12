package com.example.thymeleaf.dto;

import com.example.thymeleaf.validation.StudentField;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

import static com.example.thymeleaf.validation.StudentFieldType.*;

@Getter
@Setter
public class CreateStudentDTO {

    @StudentField(NAME)
    private String name;

    @StudentField(EMAIL)
    private String email;

    @NotNull(message = "{NotNull.birthday}")
    @Past(message = "Data urodzenia musi należeć do przeszłości.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @StudentField(ZIP_CODE)
    private String zipCode;

    @StudentField(STREET)
    private String street;

    @StudentField(NUMBER)
    private String number;

    @StudentField(COMPLEMENT)
    private String complement;

    @StudentField(DISTRICT)
    private String district;

    @StudentField(CITY)
    private String city;

    @StudentField(STATE)
    private String state;

}
