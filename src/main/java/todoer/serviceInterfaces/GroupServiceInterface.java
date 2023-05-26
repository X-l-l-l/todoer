package todoer.serviceInterfaces;

import org.springframework.http.ResponseEntity;
import todoer.group.Group;
import todoer.user.User;

import java.util.List;

public interface GroupServiceInterface {
    public List<Group> getGroups();
    Group getGroup(Long id);
    public Boolean addGroup(Group group);
    public Group updateGroup(Long groupId, String name, String desc);
    public ResponseEntity<String> deleteGroup(Long groupId);
    List<Group> getGroupsByUserId(Long userId);

    void addUserToGroup(Long groupId, String username);

    void removeUserFromGroup(Long groupId, String username);
}
