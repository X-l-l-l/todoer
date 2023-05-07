package todoer.serviceInterfaces;

import org.springframework.http.ResponseEntity;
import todoer.todolist.ToDoList;

import java.util.List;

public interface ToDoListServiceInterface {
    public List<ToDoList> getToDos();
    public Boolean addToDo(ToDoList todo);
    public ResponseEntity<String> deleteToDo(Long todoId);
    public ToDoList updateToDo(Long todoId, String title, String description);
}
