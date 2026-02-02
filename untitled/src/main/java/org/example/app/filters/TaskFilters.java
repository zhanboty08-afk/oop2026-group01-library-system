package org.example.app.filters;

import java.util.ArrayList;
import java.util.List;

public final class TaskFilters {
    private TaskFilters() {}

    public static <T> List<T> filter(List<T> list, TaskFilter<T> filter) {
        List<T> result = new ArrayList<>();
        for (T item : list) {
            if (filter.test(item)) result.add(item);
        }
        return result;
    }
}

