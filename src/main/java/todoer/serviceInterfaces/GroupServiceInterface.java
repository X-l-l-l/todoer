package todoer.serviceInterfaces;

import org.springframework.http.ResponseEntity;
import todoer.group.Group;

import java.util.List;

public interface GroupServiceInterface {
    public List<Group> getGroups();
    public Boolean addGroup(Group group);
    public Group updateGroup(Long groupId, String name, String desc);
    public ResponseEntity<String> deleteGroup(Long groupId);
}
