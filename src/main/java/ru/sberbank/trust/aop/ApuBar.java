package ru.sberbank.trust.aop;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import ru.sberbank.trust.Person;

import static lombok.AccessLevel.PRIVATE;
@Component
@FieldDefaults(level = PRIVATE)
public class ApuBar implements Bar {

    @NotNull
    @Override
    @Congrats
    @FeedBackNeeded
    public Squishee sellSquishee(@NotNull Person person) {
        if (person.isBroke())
            throw new CustomerBrokenException();
        return () -> "Usual Squishee";
    }
}