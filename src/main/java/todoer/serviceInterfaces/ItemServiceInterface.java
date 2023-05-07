package todoer.serviceInterfaces;

import org.springframework.http.ResponseEntity;
import todoer.item.Item;

import java.util.List;

public interface ItemServiceInterface {
    public List<Item> getItems();
    public Boolean addItem(Item item);

    public Item updateItem(Long itemId, String text, Boolean completed);


    public ResponseEntity<String> deleteItem(Long itemId);
}
