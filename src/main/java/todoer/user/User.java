package todoer.user;

import jakarta.persistence.*;
import todoer.item.Item;
import todoer.member.Member;
import todoer.todolist.ToDoList;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Represents the user/person with all their caracteristics
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private String email;
    private String username;
    private String password;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(Long id, String name, LocalDate dateOfBirth, String email, String username, String password) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(String name, LocalDate dateOfBirth, String email, String username, String password) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", todos=" + todos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(dateOfBirth, user.dateOfBirth) && Objects.equals(email, user.email) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(todos, user.todos) && Objects.equals(members, user.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateOfBirth, email, username, password, todos, members);
    }

    public List<ToDoList> getTodos() {
        return todos;
    }

    public void setTodos(List<ToDoList> todos) {
        this.todos = todos;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "user")
    private List<ToDoList> todos;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "user")
    private List<Member> members;
}
