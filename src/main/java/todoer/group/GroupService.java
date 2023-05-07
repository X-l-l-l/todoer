package todoer.group;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
     * @return
     */
    public Boolean addGroup(Group group) {
        groupRepository.save(group);
        return true;
    }

    /**
     * @param groupId id of the group to be updated
     * @param name    the new text of the group
     * @param desc    the new state of the group
     * @return
     */
    @Transactional
    public Group updateGroup(Long groupId, String name, String desc) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new IllegalStateException("group with id " + groupId + " does not exist"));
        if (name != null && name.length() > 0 && !Objects.equals(group.getName(), name)) {
            group.setName(name);
        }

        if (desc != null && desc.length() > 0 && !Objects.equals(group.getDescription(), desc)) {
            group.setDescription(desc);
        }
        return group;
    }


    /**
     * @param groupId id of the group to be deleted
     * @return
     */
    public ResponseEntity<String> deleteGroup(Long groupId) {
        if (!groupRepository.existsById(groupId)) {
            throw new IllegalStateException(
                    "group with id " + groupId + " does not exist");
        }
        groupRepository.deleteById(groupId);
        return new ResponseEntity<>("Group deleted", HttpStatus.OK);
    }
}
