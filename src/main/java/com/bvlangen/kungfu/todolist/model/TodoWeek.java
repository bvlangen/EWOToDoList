package com.bvlangen.kungfu.todolist.model;

import java.util.ArrayList;
import java.util.List;

public class TodoWeek {

    private final String naam;
    private final String datumVanTot;
    private final List<TodoDay> weekdays;

    public TodoWeek(final String naam, final String datumVanTot) {
        this.naam = naam;
        this.datumVanTot = datumVanTot;
        this.weekdays = new ArrayList<>(7);
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            this.weekdays.add(new TodoDay(dayOfWeek));
        }
    }

    public String getNaam() {
        return naam;
    }

    public String getDatumVanTot() {
        return datumVanTot;
    }

    public TodoDay getTodoDay(final DayOfWeek dayOfWeek) {
        return this.weekdays.get(dayOfWeek.ordinal());
    }

    public List<TodoDay> getWeekdays() {
        return weekdays;
    }
}
