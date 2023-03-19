package todoer.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todoer.todolist.ToDoList;

import java.util.List;

/**
 * Implementation of all the methodology of an item
 */
@Service
public class ItemService {
    private final ItemRepository itemRepository;

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
    }
}
