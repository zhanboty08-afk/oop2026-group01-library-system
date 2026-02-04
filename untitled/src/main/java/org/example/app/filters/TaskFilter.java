package org.example.app.filters;

public interface TaskFilter<T> {
    boolean test(T item);
}

