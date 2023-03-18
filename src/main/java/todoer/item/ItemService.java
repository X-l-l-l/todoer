package todoer.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todoer.todolist.ToDoList;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository)
    {
        this.itemRepository = itemRepository;
    }

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public void addItem(Item item){
        itemRepository.save(item);
    }
}
