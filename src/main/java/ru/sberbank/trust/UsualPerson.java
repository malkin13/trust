package ru.sberbank.trust;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.Singular;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class UsualPerson implements Person {

    @Default
    Long id = 1L;

    String name;

    Country country;

    int age;
    double height;
    boolean isProgrammer;

    @Default
    boolean isBroke;

    @Singular
    List<String> contacts;
}