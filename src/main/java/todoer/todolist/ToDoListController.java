package todoer.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;
import todoer.serviceInterfaces.ToDoListServiceInterface;

import java.util.List;

@RestController
@RequestMapping("todos")
public class ToDoListController {

    private final ToDoListServiceInterface toDoListService;

    @Autowired
    public ToDoListController(ToDoListServiceInterface toDoListService){
        this.toDoListService = toDoListService;
    }

    /**
     * @return a list of all the to do lists from the database
     */
    @GetMapping
    public List<ToDoList> getToDos()
    {
        return toDoListService.getToDos();
    }

    /**
     * @param todo the list to be added in the database
     */

    @CrossOrigin
    @PostMapping
    public void createToDo(@RequestBody ToDoList todo)
    {
        toDoListService.addToDo(todo);
    }

    @CrossOrigin
    @DeleteMapping(path = "{todoId}")
    public void deleteToDo(@PathVariable Long todoId){
        toDoListService.deleteToDo(todoId);
    }

    @PutMapping(path = "{todoId}")
    public void updateToDo(
            @PathVariable("todoId") Long todoId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description
    )
    {
        toDoListService.updateToDo(todoId, title, description);
    }
}
