package todoer.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import todoer.serviceInterfaces.ItemServiceInterface;
import todoer.todolist.ToDoList;
import todoer.todolist.ToDoListController;
import todoer.todolist.ToDoListRepository;

import java.util.List;
import java.util.Optional;

/**
 * Has all the functionalities of an item
 */
@RestController
@RequestMapping("items")
public class ItemController {
    private final ItemServiceInterface itemService;

    @Autowired
    public ItemController(ItemServiceInterface itemService){
        this.itemService = itemService;
    }

    /**
     * @return a list of items from the database
     */
    @GetMapping
    public List<Item> getItems()
    {
        return itemService.getItems();
    }

    /**
     * @param item the item that needs to be added to the database
     */
    @PostMapping
    public void addItem(@RequestBody Item item)
    {
        itemService.addItem(item);
    }

    @PutMapping(path = "{itemId}")
    public void updateItem(
            @PathVariable("itemId") Long itemId,
            @RequestParam(required = false) String text,
            @RequestParam(required = false) Boolean completed
    )
    {
        itemService.updateItem(itemId, text, completed);
    }

    @DeleteMapping(path = "{itemId}")
    public void deleteItem(@PathVariable("itemId") Long itemId){
        itemService.deleteItem(itemId);
    }

}
