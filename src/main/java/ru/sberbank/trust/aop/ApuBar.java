package ru.sberbank.trust.aop;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import ru.sberbank.trust.Person;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
public final class ApuBar implements Bar {

    @NotNull
    @Override
    public Squishee sellSquishee(@NotNull Person person) {
        if (person.isBroke())
            throw new CustomerBrokenException();
        return () -> "Usual Squishee";
    }
}