package com.example.todowebapp.todoApp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TodoServiceTest {

    @Autowired
    private TodoService todoService;
    Todo todo=new Todo(1,"Learn SpringBoot", LocalDate.now().plusMonths(3),false);
    @Test
    public void listTodosTest()
    {
        List<Todo> todo=todoService.listTodos();
        assertEquals(3,todo.size());
    }
  @Test
    public void addTodoTest()
    {
        todoService.addtodo(todo);
        List<Todo> todo=todoService.listTodos();
        assertEquals(4,todo.size());
    }

    @Test
    public void deleteTodoTest()
    {
        todoService.deleteTodo(1);
        List<Todo> todo=todoService.listTodos();
        assertEquals(2,todo.size());
    }
    @Test
    public void findByIdTest()
    {
        todoService.findById(1);
        assertEquals(1,todo.getId());
    }
    @Test
    public void updateTest()
    {
    Todo todo2=new Todo(1,"Learn SpringBoot updated", LocalDate.now().plusMonths(3),false);
    todoService.update(1,todo2);
    assertEquals("Learn SpringBoot updated",todo2.getDescription());
    }

}