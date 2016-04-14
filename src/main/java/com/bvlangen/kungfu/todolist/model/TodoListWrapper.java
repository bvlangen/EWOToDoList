package com.bvlangen.kungfu.todolist.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Helper class to wrap a list of todoweeks. This is used for saving the
 * list of todos to XML.
 */
@XmlRootElement(name = "todoYearWeeks")
public class TodoListWrapper {
    private List<TodoYearWeek> todoYearWeeks;

    @XmlElement
    public List<TodoYearWeek> getTodoYearWeeks() {
        return todoYearWeeks;
    }

    public void setTodoYearWeeks(final List<TodoYearWeek> todoYearWeeks) {
        this.todoYearWeeks = todoYearWeeks;
    }
}
