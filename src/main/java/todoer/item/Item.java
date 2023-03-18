package todoer.item;

import jakarta.persistence.*;
import todoer.todolist.ToDoList;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @SequenceGenerator(
            name = "item_sequence",
            sequenceName = "item_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_sequence"
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
                '}';
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "listId", referencedColumnName = "id")
    private ToDoList todo;
}
