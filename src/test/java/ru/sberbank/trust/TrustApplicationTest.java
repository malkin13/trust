package ru.sberbank.trust;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.sberbank.trust.aop.Bar;

import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import org.assertj.core.api.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class TrustApplicationTest {


    @Autowired
    Person vasyaPupkin;

    @Autowired
    Person ivanIvanov;

    @Autowired
    Bar bar;

    @Test
    @SneakyThrows
    @DisplayName("UsualPerson injects correctly")
    void testName() {
        // given

     //   assertThat(vasyaPupkin.getName()).isEqualTo("Вася Пупкин");
      ///  assertThat(ivanIvanov.getName()).isEqualTo("Ivan Ivanov");

        System.out.println("vasyaPupkin = " + vasyaPupkin);
        System.out.println("ivanIvanov = " + ivanIvanov);

    }



 @Test
@SneakyThrows
@DisplayName(" AOP method Correct")
void testAOP() {



 }
}