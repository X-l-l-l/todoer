package todoer.notification;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import todoer.user.User;

@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    String text;

    public Notification() {
    }

    public Notification(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public Notification(String text, User user) {
        this.text = text;
        this.user = user;
    }

    public Notification(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "userId",nullable = false , referencedColumnName = "id")
    @JsonBackReference
    private User user;
}
