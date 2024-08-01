package com.example.todowebapp.todoApp;

import com.example.todowebapp.repository.TodoRepository;
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
import java.util.Optional;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {
    private TodoService todoService;
    private TodoRepository todoRepository;

    public TodoControllerJpa(TodoService todoService,TodoRepository todoRepository) {
        this.todoService = todoService;
        this.todoRepository=todoRepository;
    }

    @GetMapping("/todos")
    public String todoList(ModelMap model) {
        List<Todo> todo =todoRepository.findAll();
        model.put("todos", todo);
        return "todolist";
    }

    @GetMapping("/addtodo")
    public String getNewTodoPage(ModelMap model) {
          Todo todo= new Todo(0,"Learn Devoops", LocalDate.now().plusMonths(1), false);
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
        todoRepository.save(todo);
        //todoService.addtodo(todo);
        return "redirect:/todos";
    }

    @GetMapping("/deletetodo/{id}")
    public String deletetodoList(@PathVariable int id) {
        todoRepository.deleteById(id);
        //todoService.deleteTodo(id);
        return "redirect:/todos";
    }

    @GetMapping("/updatetodo/{id}")
    public String updatetodoList(@PathVariable int id, Model model) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
        //  Todo todo = todoService.findById(id);
            model.addAttribute("todo", todo);
            return "todo";
    }
    @PostMapping("/updatetodo/{id}")
    public String updatedtodoList(@PathVariable int id,@Valid Todo todo ) {
       todoRepository.save(todo);
       // todoService.update(id,todo);
        return "redirect:/todos";
    }
}
