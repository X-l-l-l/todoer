package todoer.serviceInterfaces;

import jakarta.transaction.Transactional;
import todoer.todolist.ToDoList;

import java.util.List;
import java.util.Objects;

public interface ToDoListServiceInterface {
    public List<ToDoList> getToDos();
    public void addToDo(ToDoList todo);
    public void deleteToDo(Long todoId);
    public void updateToDo(Long todoId, String title, String description);
}
