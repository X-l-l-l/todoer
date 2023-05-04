package todoer.todolist;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todoer.item.Item;
import todoer.serviceInterfaces.ToDoListServiceInterface;

import java.util.List;
import java.util.Objects;

/**
 * Implementations of the todolist methodologies
 */
@Service
public class ToDoListService implements ToDoListServiceInterface {

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

    /**
     * @param todoId id of the list to be deleted
     */
    public void deleteToDo(Long todoId) {
        if (!toDoListRepository.existsById(todoId)) {
            throw new IllegalStateException(
                    "List with id " + todoId + " does not exist");
        }
        toDoListRepository.deleteById(todoId);
    }

    /**
     * @param todoId id of the list to be updated
     * @param title the new title
     * @param description the new description
     */
    @Transactional
    public void updateToDo(Long todoId, String title, String description) {
        ToDoList list = toDoListRepository.findById(todoId).orElseThrow(() -> new IllegalStateException("List with id " + todoId + " does not exist"));
        if (title != null && title.length() > 0 && !Objects.equals(list.getTitle(), title)) {
            list.setTitle(title);
        }

        if (description != null && description.length() > 0 && !Objects.equals(list.getDescription(), description)) {
            list.setDescription(description);
        }
    }
}
