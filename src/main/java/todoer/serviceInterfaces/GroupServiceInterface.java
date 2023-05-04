package todoer.serviceInterfaces;

import jakarta.transaction.Transactional;
import todoer.group.Group;

import java.util.List;
import java.util.Objects;

public interface GroupServiceInterface {
    public List<Group> getGroups();
    public void addGroup(Group group);
    public void updateGroup(Long groupId, String name, String desc);
    public void deleteGroup(Long groupId);
}
