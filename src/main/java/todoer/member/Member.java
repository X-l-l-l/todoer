package todoer.member;

import jakarta.persistence.*;
import todoer.group.Group;
import todoer.user.User;

@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "groupId")
    private Group group;
    Boolean leader;

    public Member(Long id, User user, Group group, Boolean leader) {
        this.id = id;
        this.user = user;
        this.group = group;
        this.leader = leader;
    }

    public Member() {
    }

    public Member(User user, Group group, Boolean leader) {
        this.user = user;
        this.group = group;
        this.leader = leader;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Boolean getLeader() {
        return leader;
    }

    public void setLeader(Boolean leader) {
        this.leader = leader;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", user=" + user +
                ", group=" + group +
                ", leader=" + leader +
                '}';
    }
}
