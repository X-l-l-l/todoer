package todoer.item;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import todoer.todolist.ToDoList;

/**
 * Used to implement each of the to-do list items
 */
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String text;
    private Boolean completed;

    public Item(Long id, String text, Boolean completed) {
        this.id = id;
        this.text = text;
        this.completed = completed;
    }

    public Item() {
    }

    public Item(String text, Boolean completed) {
        this.text = text;
        this.completed = completed;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", completed=" + completed +
                ", todo=" + todo +
                '}';
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "listId",nullable = false , referencedColumnName = "id")
    private ToDoList todo;

    @JsonBackReference
    public ToDoList getTodo() {
        return todo;
    }

    public void setTodo(ToDoList todo) {
        this.todo = todo;
    }
}
