package ru.sberbank.trust.dao;

import ru.sberbank.trust.common.StreamUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface Dao<ID extends Number, T extends Identifiable<ID>> {


    default Optional<T> getOne(ID id) {
        return findAll().stream()
                .filter(entity -> entity.getId().equals(id))
                .findFirst();
    }

    List<T> findAll();

    default int count() {
        return findAll().size();
    }

    default <S extends T> S save(S entity) {
        return entity.getId() == null ? insert(entity) : update(entity);
    }

    <S extends T> S update(S entity);

    <S extends T> S insert(S entity);

    default <S extends T> List<S> saveAll(Iterable<S> entities) {
        return StreamUtils.toStream(entities)
                .map(this::save)
                .collect(Collectors.toList());
    }

    Dao<ID, T> delete(ID id);

    default Dao<ID, T> delete(T entity) {
        return delete(entity.getId());
    }

    default Dao<ID, T> deleteAll() {
        findAll().forEach(this::delete);
        return this;
    }


}
