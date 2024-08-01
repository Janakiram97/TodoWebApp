package com.example.todowebapp.todoApp;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

//@Controller
@SessionAttributes("name")
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todos")
    public String todoList(ModelMap model) {
        List<Todo> todo = todoService.listTodos();
        model.put("todos", todo);
        return "todolist";
    }

    @GetMapping("/addtodo")
    public String getNewTodoPage(ModelMap model) {
          Todo todo= new Todo(0,"Learn Devoops", LocalDate.now().plusMonths(3), false);
        model.put("todo",todo );
        return "todo";
    }

    @PostMapping("/addtodo")
    public String addNewTodoPage(@Valid Todo todo, BindingResult result,Model model) {
        if(result.hasErrors()) {
            model.addAttribute("error",result.getFieldError().getDefaultMessage());
            return "todo";
        }
        System.out.println("Description is " + todo.getDescription());
        System.out.println("Target Date is " + todo.getTargetedDate());
        todoService.addtodo(todo);
        return "redirect:/todos";
    }

    @GetMapping("/deletetodo/{id}")
    public String deletetodoList(@PathVariable int id) {
        todoService.deleteTodo(id);
        return "redirect:/todos";
    }

    @GetMapping("/updatetodo/{id}")
    public String updatetodoList(@PathVariable int id, Model model) {
        Todo todo = todoService.findById(id);
        model.addAttribute("todo", todo);
        return "todo";
    }
    @PostMapping("/updatetodo/{id}")
    public String updatedtodoList(@PathVariable int id,@Valid Todo todo ) {

        todoService.update(id,todo);
        return "redirect:/todos";
    }
}
