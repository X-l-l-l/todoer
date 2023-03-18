package todoer.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import todoer.todolist.ToDoList;
import todoer.todolist.ToDoListController;
import todoer.todolist.ToDoListRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("items")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> getItems()
    {
        return itemService.getItems();
    }

    @PostMapping
    public void addItem(@RequestBody Item item)
    {
        itemService.addItem(item);
    }

}
