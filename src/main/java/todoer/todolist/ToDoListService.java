package todoer.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoListService {

    private final ToDoListRepository toDoListRepository;

    @Autowired
    public ToDoListService(ToDoListRepository toDoListRepository) {
        this.toDoListRepository = toDoListRepository;
    }

    public List<ToDoList> getToDos() {
        return toDoListRepository.findAll();
    }

    public void addToDo(ToDoList todo) {
        toDoListRepository.save(todo);
    }
}
