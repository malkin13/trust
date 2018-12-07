package ru.sberbank.trust;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor
@SpringBootApplication
@ImportResource("classpath:ioc.xml")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class TrustApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrustApplication.class, args);
    }

    @Bean
    Country belarus() {
        return Country.builder()
                .id(3)
                .name("Беларуссия")
                .groupName("BEL")
                .build();
    }

    @Bean
    Person vasyaPupkin(Country belarus) {
        return UsualPerson.builder()
                .id(2L)
                .name("Вася Пупкин")
                .age(15)
                .contact("222-33-22")
                .contact("kljhdg@sdfg.ru")
                .height(1.78)
                .country(belarus)
                .build();
    }
}

