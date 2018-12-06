package ru.sberbank.trust;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@SpringBootApplication
public class TrustApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrustApplication.class, args);
    }

    @Bean
    Country russia() {
        return new Country("Rossia","PinkFloyd");
    }

    @Bean
    Person person(Country russia) {
        return Person.builder()
                .name("Вася пупкин")
               .age(15)
               .contact("222-33-22")
                .contact("1@1.com")
                .height(1.4)
                .country(russia)
               .build() ;
    }
}

@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
class Country {

    Integer id;

    String name;

    String gropNaame;

    public Country(String name, String gropNaame) {
        this.name = name;
        this.gropNaame = gropNaame;
    }

}

@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
class Person {
    Long id;

    String name;

    Country country;

    int age;
    double height;
    boolean isProgrammer;
    @Singular
    List<String> contacts;
}

