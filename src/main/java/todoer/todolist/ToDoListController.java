package todoer.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("todos")
public class ToDoListController {

    private final ToDoListService toDoListService;

    @Autowired
    public ToDoListController(ToDoListService toDoListService){
        this.toDoListService = toDoListService;
    }

    @GetMapping
    public List<ToDoList> getToDos()
    {
        return toDoListService.getToDos();
    }

    @PostMapping
    public void createToDo(@RequestBody ToDoList todo)
    {
        toDoListService.addToDo(todo);
    }
}
