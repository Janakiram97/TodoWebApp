package com.example.todowebapp.todoApp;

import com.example.todowebapp.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class TodoControllerJpaTest {
    @Mock
    private TodoRepository repository;
    @Mock
    private  ModelMap model;
    @Mock
    private Model model1;

    @Mock
    private BindingResult result;
    @InjectMocks
    private TodoControllerJpa subject;

    Todo todo=new Todo(1,"Learn Devoops", java.time.LocalDate.now().plusMonths(1), false);
    @Test
    public void todoListTest()
    {
        String todoList = subject.todoList(model);
       // assertEquals("todolist",todoList);
        assert (todoList.equals("todolist"));
    }
    @Test
   public void getNewTodoPageTest()
   {
       String newTodoPage = subject.getNewTodoPage(model);
       assertEquals("todo",newTodoPage);
   }
   @Test
   public void addNewTodoPageTest()
   {
       when(result.hasErrors()).thenReturn(false);
       String addNewTodoPage = subject.addNewTodoPage(new Todo(), result, model1);
       assert(addNewTodoPage.equals("redirect:/todos"));
       //assertEquals("redirect:/todos",addNewTodoPage);
   }

   @Test
    public void deleteTodoListTest()
   {
       String deletetodoList = subject.deletetodoList(1);
       assert(deletetodoList.equals("redirect:/todos"));
   }

   @Test
   public void updateTodoTest()
   {
       when(repository.findById(1)).thenReturn(Optional.of(todo));
       String updatetodoList = subject.updatetodoList(1, model1);
       //assertEquals("todo",updatetodoList);
       assert(updatetodoList.equals("todo"));
   }
   @Test
   public void updatedTodoTest()
   {
       String updatedtodoList = subject.updatedtodoList(1, new Todo());
       assert(updatedtodoList.equals("redirect:/todos"));
   }
}