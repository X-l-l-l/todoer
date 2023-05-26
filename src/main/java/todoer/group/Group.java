package todoer.group;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import todoer.item.Item;
import todoer.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Represents the group with all their caracteristics
 */
@Entity
@Table(name = "grps")
public class Group {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String name;
    private String description;

    public Group(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Group(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Group(Long id) {
        this.id = id;
    }

    public Group() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", items=" + items +
                '}';
    }

    public void addUser(User user) {
        this.users.add(user);
        user.getGroups().add(this);
    }

    public void removeUser(Long userId) {
        User user = this.users.stream().filter(t -> t.getId() == userId).findFirst().orElse(null);
        if (user != null) {
            this.users.remove(user);
            user.getGroups().remove(this);
        }
    }
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "group_user",
            joinColumns = { @JoinColumn(name = "group_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private Set<User> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(id, group.id) && Objects.equals(name, group.name) && Objects.equals(description, group.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "group")
    @JsonManagedReference(value = "grp")
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
