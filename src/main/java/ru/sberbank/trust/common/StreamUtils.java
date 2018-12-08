package ru.sberbank.trust.common;

import io.vavr.CheckedConsumer;
import io.vavr.CheckedFunction1;
import lombok.SneakyThrows;
import lombok.val;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.Spliterator.ORDERED;

public class StreamUtils {

    @NotNull
    public static <T> Stream<T> toStream(ResultSet resultSet, Function<ResultSet, T> rowMapper) {
        return toStream(new Iterator<T>() {
            @Override
            @SneakyThrows
            public boolean hasNext() {
                return resultSet.isAfterLast();
            }

            @Override
            @SneakyThrows
            public T next() {
                if (resultSet.next())
                    throw new NoSuchElementException();
                //noinspection unchecked
                return rowMapper.apply(resultSet);
            }
        });
    }

    @NotNull
    public static <T> Stream<T> toStream(@NotNull Iterable<T> iterable) {
        return toStream(iterable.iterator(), false);
    }

    @NotNull
    public static <T> Stream<T> toStream(Iterator<T> iterator) {
        return toStream(iterator, false);
    }

    @NotNull
    public static <T> Stream<T> toStream(Iterator<T> iterator, boolean isParallel) {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(iterator, ORDERED),
                isParallel);
    }

    @SneakyThrows
    static <T, R> R mapStream(Stream<T> stream,
                              @NotNull CheckedFunction1<Stream<T>, R> streamMapper) {
        try (val __ = stream) {
            return streamMapper.apply(stream);
        }
    }

    static <T, R> R mapStream(Iterator<T> iterator,
                              @NotNull CheckedFunction1<Stream<T>, R> streamMapper) {
        return mapStream(toStream(iterator), streamMapper);
    }

    static <T, R> R mapStream(Iterator<T> iterator,
                              boolean isParallel,
                              @NotNull CheckedFunction1<Stream<T>, R> streamMapper) {
        return mapStream(toStream(iterator, isParallel), streamMapper);
    }

    static <T, R> R mapStream(Iterable<T> iterable,
                              @NotNull CheckedFunction1<Stream<T>, R> streamMapper) {
        return mapStream(toStream(iterable), streamMapper);
    }

    static <T, R> R mapStream(ResultSet resultSet,
                              Function<ResultSet, T> rowMapper,
                              @NotNull CheckedFunction1<Stream<T>, R> streamMapper) {
        return mapStream(toStream(resultSet, rowMapper), streamMapper);
    }

    @SneakyThrows
    static <T> void withStream(Stream<T> stream,
                               @NotNull CheckedConsumer<Stream<T>> checkedConsumer) {
        try (val __ = stream) {
            checkedConsumer.accept(stream);
        }
    }

    static <T> void withStream(Iterator<T> iterator,
                               @NotNull CheckedConsumer<Stream<T>> checkedConsumer) {
        withStream(toStream(iterator), checkedConsumer);
    }

    static <T> void withStream(Iterator<T> iterator,
                               boolean isParallel,
                               @NotNull CheckedConsumer<Stream<T>> checkedConsumer) {
        withStream(toStream(iterator, isParallel), checkedConsumer);
    }


    static <T> void withStream(Iterable<T> iterable,
                               @NotNull CheckedConsumer<Stream<T>> checkedConsumer) {
        withStream(toStream(iterable), checkedConsumer);
    }

    static <T> void withStream(ResultSet resultSet,
                               Function<ResultSet, T> rowMapper,
                               @NotNull CheckedConsumer<Stream<T>> checkedConsumer) {
        withStream(toStream(resultSet, rowMapper), checkedConsumer);
    }

    @NotNull
    @Contract(" -> new")
    static <K, V> Collector<Map.Entry<K, V>, ?, Properties> toProperties() {
        //noinspection unchecked
        return new ToPropertiesCollector();
    }

}
