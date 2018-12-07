package ru.sberbank.trust.aop;

import ru.sberbank.trust.Person;

@FunctionalInterface
public interface Bar {

    Squishee sellSquishee(Person person);
}
