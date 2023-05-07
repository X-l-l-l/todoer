package todoer;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import todoer.item.Item;
import todoer.item.ItemRepository;
import todoer.item.ItemService;
import todoer.item.events.NewItemEvent;
import todoer.serviceInterfaces.ItemServiceInterface;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ItemTests {
    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    List<Item> mockItems = Arrays.asList(new Item(1L,"item1",true), new Item(2L,"item2",true));

    @Test
    public void addItemTest() {
        ItemServiceInterface itemService = new ItemService(itemRepository, applicationEventPublisher);
        Item item = mockItems.get(0);

        var event = mock(NewItemEvent.class);
        doNothing().when(applicationEventPublisher).publishEvent(event);

        when(itemRepository.save(any(Item.class))).thenReturn(new Item());
        Boolean result = itemService.addItem(item);

        assertTrue(result);
        verify(itemRepository).save(item);
    }
    @Test
    public void getItemTest() {
        ItemServiceInterface itemService = new ItemService(itemRepository, applicationEventPublisher);
        when(itemRepository.findAll()).thenReturn(mockItems);

        List<Item> items = itemService.getItems();
        assertEquals(items, mockItems);
        verify(itemRepository).findAll();
    }

    @Test
    public void deleteItemTest() {
        ItemServiceInterface itemService = new ItemService(itemRepository, applicationEventPublisher);
        Item item = mockItems.get(0);

        when(itemRepository.existsById(item.getId())).thenReturn(true);
        ResponseEntity<String> result = itemService.deleteItem(item.getId());

        assertEquals(result.getStatusCode(), HttpStatus.OK);
        verify(itemRepository).existsById(item.getId());
    }

    @Test
    public void updateItemTest() {
        ItemServiceInterface itemService = new ItemService(itemRepository, applicationEventPublisher);
        Item item = mockItems.get(0);

        Item itemafterupdate = new Item(1L,"newitem",false);

        when(itemRepository.findById(item.getId())).thenReturn(Optional.of(item));

        Item itemUpdated = itemService.updateItem(item.getId(), "newitem", false);

        assertEquals(itemUpdated, itemafterupdate);

        verify(itemRepository).findById(item.getId());
    }
}
