package com.bvlangen.kungfu.todolist.pdf;

public class TodoListException extends Exception {

    private static final long serialVersionUID = -4432091190735385490L;

    public TodoListException(final String message) {
        super(message);
    }

    public TodoListException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
