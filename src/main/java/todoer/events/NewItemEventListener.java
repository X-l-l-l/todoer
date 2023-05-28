package todoer.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;
import todoer.group.Group;
import todoer.group.GroupService;
import todoer.item.Item;
import todoer.item.events.NewItemEvent;
import todoer.notification.Notification;
import todoer.notification.NotificationService;
import todoer.todolist.ToDoList;
import todoer.todolist.ToDoListRepository;
import todoer.user.User;
import todoer.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * The observer, the class that waits for the event
 */
@Component
public class NewItemEventListener {

    @Autowired
    NotificationService notificationService;

    @Autowired
    GroupService groupService;

    /**
     * @param newItemEvent receives a newItemEvent which is the event launched by the ItemEvent
     * It takes the new item that is created and searches for the list and user the item was added to and then sends a notification
     *                     to the console (for now)
     */
    @EventListener
    public void handleNewItem(final NewItemEvent newItemEvent){
        Item item = newItemEvent.getItem();
        Group group = groupService.getGroup(item.getGroup().getId());
        Set<User> users = group.getUsers();
        for (User user:users
             ) {
            Notification notification = new Notification("New item in " + group.getName(), user);
            notificationService.createNotification(notification);
        }
    }
}
