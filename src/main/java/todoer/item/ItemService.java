package todoer.item;

import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
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
    public ItemService(ItemRepository itemRepository) {
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
    public void addItem(Item item) {
        itemRepository.save(item);
        applicationEventPublisher.publishEvent(new NewItemEvent(this, item));
    }

    /**
     * @param itemId    id of the item to be updated
     * @param text      the new text of the item
     * @param completed the new state of the item
     */
    @Transactional
    public void updateItem(Long itemId, String text, Boolean completed) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new IllegalStateException("item with id " + itemId + " does not exist"));
        if (text != null && text.length() > 0 && !Objects.equals(item.getText(), text)) {
            item.setText(text);
        }

        if (completed != null && !Objects.equals(item.getCompleted(), completed)) {
            item.setCompleted(completed);
        }
    }


    /**
     * @param itemId id of the item to be deleted
     */
    public void deleteItem(Long itemId) {
        if (!itemRepository.existsById(itemId)) {
            throw new IllegalStateException(
                    "item with id " + itemId + " does not exist");
        }
        itemRepository.deleteById(itemId);
    }
}
