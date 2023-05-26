package todoer.item;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import todoer.item.events.NewItemEvent;
import todoer.serviceInterfaces.ItemServiceInterface;

import java.util.List;
import java.util.Objects;

/**
 * Implementation of all the methodology of an item
 */
@Service
public class ItemService implements ItemServiceInterface {
    private final ItemRepository itemRepository;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public ItemService(ItemRepository itemRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.itemRepository = itemRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * @return a list of items from the database
     */
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    /**
     * @param item that needs to be added to the database
     * @return
     */
    public Boolean addItem(Item item) {
        itemRepository.save(item);
//        applicationEventPublisher.publishEvent(new NewItemEvent(this, item));
        return true;
    }



    /**
     * @param itemId    id of the item to be updated
     * @param text      the new text of the item
     * @param completed the new state of the item
     * @return
     */
    @Transactional
    public Item updateItem(Long itemId, String text, Boolean completed) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new IllegalStateException("item with id " + itemId + " does not exist"));
        if (text != null && text.length() > 0 && !Objects.equals(item.getText(), text)) {
            item.setText(text);
        }

        if (completed != null && !Objects.equals(item.getCompleted(), completed)) {
            item.setCompleted(completed);
        }
        return item;
    }


    /**
     * @param itemId id of the item to be deleted
     * @return
     */
    public ResponseEntity<String> deleteItem(Long itemId) {
        if (!itemRepository.existsById(itemId)) {
            throw new IllegalStateException(
                    "item with id " + itemId + " does not exist");
        }
        itemRepository.deleteById(itemId);
        return new ResponseEntity<>("Item deleted", HttpStatus.OK);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemService that = (ItemService) o;
        return Objects.equals(itemRepository, that.itemRepository) && Objects.equals(applicationEventPublisher, that.applicationEventPublisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemRepository, applicationEventPublisher);
    }
}
