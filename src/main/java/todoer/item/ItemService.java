package todoer.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import todoer.item.events.NewItemEvent;
import todoer.todolist.ToDoList;
import todoer.user.User;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Implementation of all the methodology of an item
 */
@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public ItemService(ItemRepository itemRepository)
    {
        this.itemRepository = itemRepository;
    }

    /**
     * @return a list of items from the database
     */
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    /**
     * @param item that needs to be added to the database
     */
    public void addItem(Item item){
        itemRepository.save(item);
        applicationEventPublisher.publishEvent(new NewItemEvent(this, item));
    }

    public void updateItem(Long itemId, String text, Boolean completed) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new IllegalStateException("user with id "+itemId+" does not exist"));
        if(text != null && text.length()>0 && !Objects.equals(item.getText(), text)){
            item.setText(text);
        }

        if(completed != null && !Objects.equals(item.getCompleted(), completed)){
            item.setCompleted(completed);
        }
    }
}
