package todoer.todolist;

import jakarta.persistence.*;
import todoer.item.Item;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "todos")
public class ToDoList {
    @Id
    @SequenceGenerator(
            name = "todo_sequence",
            sequenceName = "todo_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "todo_sequence"
    )
    private Long id;
    private String title;
    private String description;

    public ToDoList(Long id, String title, String description, List<Item> items) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.items = items;
    }

    public ToDoList() {
    }

    public ToDoList(String title, String description, List<Item> items) {
        this.title = title;
        this.description = description;
        this.items = items;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "List{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", items=" + items +
                '}';
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "todo")
    private List<Item> items;

}
