package todoer.group;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todoer.serviceInterfaces.GroupServiceInterface;

import java.util.List;
import java.util.Objects;

/**
 * Implementation of all the methodology of a group
 */
@Service
public class GroupService implements GroupServiceInterface {
    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    /**
     * @return a list of groups from the database
     */
    public List<Group> getGroups() {
        return groupRepository.findAll();
    }

    /**
     * @param group that needs to be added to the database
     */
    public void addGroup(Group group) {
        groupRepository.save(group);
    }

    /**
     * @param groupId    id of the group to be updated
     * @param name      the new text of the group
     * @param desc the new state of the group
     */
    @Transactional
    public void updateGroup(Long groupId, String name, String desc) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new IllegalStateException("group with id " + groupId + " does not exist"));
        if (name != null && name.length() > 0 && !Objects.equals(group.getName(), name)) {
            group.setName(name);
        }

        if (desc != null && desc.length() > 0 && !Objects.equals(group.getDescription(), desc)) {
            group.setDescription(desc);
        }
    }


    /**
     * @param groupId id of the group to be deleted
     */
    public void deleteGroup(Long groupId) {
        if (!groupRepository.existsById(groupId)) {
            throw new IllegalStateException(
                    "group with id " + groupId + " does not exist");
        }
        groupRepository.deleteById(groupId);
    }
}
