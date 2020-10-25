package ru.akirakozov.sd.refactoring.database;

@FunctionalInterface
public interface UncheckedConsumer<T> {
    void accept(T arg) throws Exception;
}
