package ru.sberbank.trust.dao;

public interface Identifiable <ID extends Number> {

    ID getId();

    Identifiable<ID> setId(ID id);

}
