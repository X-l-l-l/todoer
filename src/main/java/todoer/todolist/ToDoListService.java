package todoer.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementations of the todolist methodologies
 */
@Service
public class ToDoListService {

    private final ToDoListRepository toDoListRepository;

    @Autowired
    public ToDoListService(ToDoListRepository toDoListRepository) {
        this.toDoListRepository = toDoListRepository;
    }

    /**
     * @return a list of all todolists from the database
     */
    public List<ToDoList> getToDos() {
        return toDoListRepository.findAll();
    }

    /**
     * @param todo that will be added in the database
     */
    public void addToDo(ToDoList todo) {
        toDoListRepository.save(todo);
    }
}
