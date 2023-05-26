package todoer.todolist;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import todoer.item.Item;
import todoer.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Representation of the list of tasks
 */
@Entity
@Table(name = "todos")
public class ToDoList {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String title;
    private String description;

    public ToDoList(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public ToDoList() {
    }

    public ToDoList(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ToDoList(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @JsonBackReference
    public User getUser() {
        return user;
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ToDoList{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", items=" + items +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDoList toDoList = (ToDoList) o;
        return Objects.equals(id, toDoList.id) && Objects.equals(title, toDoList.title) && Objects.equals(description, toDoList.description) && Objects.equals(items, toDoList.items) && Objects.equals(user, toDoList.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, items, user);
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "todo")
    @JsonManagedReference(value = "lst")
    private List<Item> items;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "userId",nullable = false , referencedColumnName = "id")
    private User user;

}
