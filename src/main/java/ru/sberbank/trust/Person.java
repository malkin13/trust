package ru.sberbank.trust;

public interface Person {
    Long getId();

    String getName();

    Country getCountry();

    int getAge();

    double getHeight();

    boolean isProgrammer();

    boolean isBroke();

    java.util.List<String> getContacts();
}