package todoer.serviceInterfaces;

import jakarta.transaction.Transactional;
import todoer.item.Item;
import todoer.item.events.NewItemEvent;

import java.util.List;
import java.util.Objects;

public interface ItemServiceInterface {
    public List<Item> getItems();
    public void addItem(Item item);

    public void updateItem(Long itemId, String text, Boolean completed);


    public void deleteItem(Long itemId);
}
