package todoer.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import todoer.serviceInterfaces.GroupServiceInterface;

import java.util.List;


/**
 * Has all the functionalities of a group
 */
@RestController
@RequestMapping("group")
public class GroupController {
    private final GroupServiceInterface groupService;

    @Autowired
    public GroupController(GroupServiceInterface groupService) {
        this.groupService = groupService;
    }

    /**
     * @param group the group that needs to be added to the database
     */
    @PostMapping
    public void addGroup(@RequestBody Group group)
    {
        groupService.addGroup(group);
    }

    /**
     * @return a list of all groups in the database
     */
    @GetMapping
    public List<Group> getGroups() {
        return groupService.getGroups();
    }

    /**
     * @param groupId by which the group in the database, if it exists, is deleted
     */
    @DeleteMapping(path = "{groupId}")
    public void deleteGroup(@PathVariable("groupId") Long groupId) {
        groupService.deleteGroup(groupId);
    }

    /**
     * @param groupId the id of the group that is updated
     * @param name the new name
     * @param description the new email
     */
    @PutMapping(path = "{groupId}")
    public void updateGroup(
            @PathVariable("groupId") Long groupId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description
    )
    {
        groupService.updateGroup(groupId, name, description);
    }
}
