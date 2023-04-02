package todoer.user.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import todoer.item.events.NewItemEvent;

@Component
public class NewItemEventListener {

    @EventListener
    public void handleNewItem(final NewItemEvent newItemEvent){
        System.out.println("New item " + newItemEvent.getItem().getText());
    }
}
