package todoer.group;

import jakarta.persistence.*;
import todoer.member.Member;

import java.util.List;
import java.util.Objects;

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


    public Group(Long id) {
        this.id = id;
    }

    public Group() {
    }

    public Group(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Group(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", members=" + members +
                '}';
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "group")
    private List<Member> members;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(id, group.id) && Objects.equals(name, group.name) && Objects.equals(description, group.description) && Objects.equals(members, group.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, members);
    }
}
