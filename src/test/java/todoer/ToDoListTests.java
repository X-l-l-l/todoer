package todoer;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import todoer.serviceInterfaces.ToDoListServiceInterface;
import todoer.todolist.ToDoList;
import todoer.todolist.ToDoListRepository;
import todoer.todolist.ToDoListService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ToDoListTests {
    @Mock
    private ToDoListRepository toDoListRepository;

    List<ToDoList> mockLists = Arrays.asList(new ToDoList(1L,"list1","desc1"), new ToDoList(2L,"list2","desc2"));

    @Test
    public void addToDoListTest() {
        ToDoListServiceInterface toDoListService = new ToDoListService(toDoListRepository);
        ToDoList toDoList = mockLists.get(0);

        when(toDoListRepository.save(any(ToDoList.class))).thenReturn(new ToDoList());
        Boolean result = toDoListService.addToDo(toDoList);

        assertTrue(result);
        verify(toDoListRepository).save(toDoList);
    }
    @Test
    public void getToDoListTest() {
        ToDoListServiceInterface toDoListService = new ToDoListService(toDoListRepository);
        when(toDoListRepository.findAll()).thenReturn(mockLists);

        List<ToDoList> toDoLists = toDoListService.getToDos();
        assertEquals(toDoLists, mockLists);
        verify(toDoListRepository).findAll();
    }

    @Test
    public void deleteToDoListTest() {
        ToDoListServiceInterface toDoListService = new ToDoListService(toDoListRepository);
        ToDoList toDoList = mockLists.get(0);

        when(toDoListRepository.existsById(toDoList.getId())).thenReturn(true);
        ResponseEntity<String> result = toDoListService.deleteToDo(toDoList.getId());

        assertEquals(result.getStatusCode(), HttpStatus.OK);
        verify(toDoListRepository).existsById(toDoList.getId());
    }

    @Test
    public void updateToDoListTest() {
        ToDoListServiceInterface toDoListService = new ToDoListService(toDoListRepository);
        ToDoList toDoList = mockLists.get(0);

        ToDoList listafterupdate = new ToDoList(1L,"newlist","newdesc");

        when(toDoListRepository.findById(toDoList.getId())).thenReturn(Optional.of(toDoList));

        ToDoList listUpdated = toDoListService.updateToDo(toDoList.getId(), "newlist", "newdesc");

        assertEquals(listUpdated, listafterupdate);

        verify(toDoListRepository).findById(toDoList.getId());
    }
}
