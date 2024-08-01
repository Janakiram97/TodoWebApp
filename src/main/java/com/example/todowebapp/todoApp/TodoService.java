package com.example.todowebapp.todoApp;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {

    private static final List<Todo> todoList = new ArrayList<>();
    static int todoCount = 0;

    static {
        todoList.add(new Todo(++todoCount, "Lean SpringBoot",
                LocalDate.now().plusMonths(3), false));
        todoList.add(new Todo(++todoCount, "Lean Microservices",
                LocalDate.now().plusMonths(5), false));
        todoList.add(new Todo(++todoCount, "Lean SpringSecurity",
                LocalDate.now().plusMonths(3), false));

    }

    public List<Todo> listTodos() {
        return todoList;
    }

    public void addtodo(Todo todo) {
        Todo todo2 = new Todo(++todoCount, todo.getDescription(), todo.getTargetedDate(), false);
        todoList.add(todo2);
    }

    public void deleteTodo(int id) {
        Predicate<Todo> predicate = todo -> todo.getId() == id;
        todoList.removeIf(predicate);
    }

    public Todo findById(int id) {
        Predicate<Todo> predicate=todo -> todo.getId() == id;
        Todo todo = todoList.stream().filter(predicate).findFirst().get();
        return todo;
    }

    public Todo update(int id,Todo todo) {
        Todo todo2 = findById(id);
        todo2.setDescription(todo.getDescription());
        todo2.setTargetedDate(todo.getTargetedDate());
        return todo2 ;
    }
}
