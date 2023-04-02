package todoer.user.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;
import todoer.item.events.NewItemEvent;
import todoer.todolist.ToDoList;
import todoer.todolist.ToDoListRepository;
import todoer.user.User;
import todoer.user.UserRepository;

@Component
public class NewItemEventListener {

    @Autowired
    private ToDoListRepository toDoListRepository;
    @Autowired
    private UserRepository userRepository;

    @EventListener
    public void handleNewItem(final NewItemEvent newItemEvent){
        ToDoList list = toDoListRepository.findById(newItemEvent.getItem().getTodo().getId()).orElseThrow(() -> new IllegalStateException("list doesn't exist"));
        User user = userRepository.findById(list.getUser().getId()).orElseThrow(() -> new IllegalStateException("user does not exist"));
        System.out.println(user.getName() + ":New item " + newItemEvent.getItem().getText());
    }
}
