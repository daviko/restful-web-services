package com.in28minutes.rest.webservices.restfulwebservices.todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TodoHardcodedService {

    private static List<Todo> todos = new ArrayList<>();
    private static long idCounter = 1;
    private static final String USER_NAME = "in28minutes";

    static {
        todos.add(new Todo(idCounter++, USER_NAME, "Learn to dance", new Date(), false));
        todos.add(new Todo(idCounter++, USER_NAME, "Learn to cook", new Date(), false));
        todos.add(new Todo(idCounter++, USER_NAME, "Learn to drive", new Date(), false));
    }

    public List<Todo> findAll() {
        return todos;
    }

    public Todo deleteById(long id) {
        Todo todo = findById(id);
        todos.remove(todo);
        return todo;
    }

    public Todo findById(long id) {
        return todos.stream().filter(todo -> todo.getId() == id).findFirst().orElse(null);
    }

    public Todo save(Todo todo) {
        if (todo.getId() == -1 || todo.getId() == 0) {
            todo.setId(++idCounter);
            todos.add(todo);
        } else {
            deleteById(todo.getId());
            todos.add(todo);
        }
        return todo;
    }

}
