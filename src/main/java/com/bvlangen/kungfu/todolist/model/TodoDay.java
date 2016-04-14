package com.bvlangen.kungfu.todolist.model;

import java.util.ArrayList;
import java.util.List;

public class TodoDay {

    private final List<Todo> doneToday;
    private final DayOfWeek dayOfWeek;

    TodoDay(final DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        this.doneToday = new ArrayList<>(Todo.values().length);
    }

    public void done(final Todo todo) {
        this.doneToday.add(todo);
    }

    public List<Todo> getDoneToday() {
        return doneToday;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
}
