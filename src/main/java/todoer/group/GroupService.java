package todoer.group;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import todoer.serviceInterfaces.GroupServiceInterface;
import todoer.user.User;
import todoer.user.UserRepository;

import java.util.*;

/**
 * Implementation of all the methodology of a group
 */
@Service
public class GroupService implements GroupServiceInterface {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    /**
     * @return a list of groups from the database
     */
    public List<Group> getGroups() {
        List<Group> groups = groupRepository.findAll();
        return groups;
    }

    @Override
    public Group getGroup(Long id) {
        return groupRepository.getGroupById(id).get();
    }

    /**
     * @param group that needs to be added to the database
     * @return
     */
    public Boolean addGroup(Group group) {
        User user = userRepository.getUserById(group.getUsers().stream().toList().get(0).getId());
        Set<Group> groups = new HashSet<>();
        groups.add(group);
        user.setGroups(groups);
        groupRepository.save(group);
        return true;
    }



    @Override
    public List<Group> getGroupsByUserId(Long userId) {
        return groupRepository.findGroupsByUsersId(userId);
    }

    @Override
    public void addUserToGroup(Long groupId, String username) {
        Optional<User> user = userRepository.findUserByUsername(username);
        if (user.isPresent()){
            Group group = groupRepository.getGroupById(groupId).get();
            group.addUser(user.get());
        }
        else throw new IllegalStateException("User not exist");

    }

    @Override
    public void removeUserFromGroup(Long groupId, String username) {
        Optional<User> user = userRepository.findUserByUsername(username);
        if (user.isPresent()){
            Group group = groupRepository.getGroupById(groupId).get();
            group.removeUser(user.get().getId());
        }
        else throw new IllegalStateException("User not exist");
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
