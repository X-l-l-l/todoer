package todoer.todolist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import todoer.item.Item;

import java.util.Optional;

/**
 * Implements all the methods through which fields from the todolist table are gotten
 */
@Repository
public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {
    Optional<ToDoList> findToDoListByTitle(String title);
}
