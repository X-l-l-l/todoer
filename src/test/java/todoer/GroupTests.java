package todoer;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import todoer.group.Group;
import todoer.group.GroupRepository;
import todoer.group.GroupService;
import todoer.serviceInterfaces.GroupServiceInterface;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GroupTests {
    @Mock
    private GroupRepository groupRepository;

    List<Group> mockGroups = Arrays.asList(new Group(1L,"group1","desc1"), new Group(2L,"group2","desc2"));

    @Test
    public void addGroupTest() {
        GroupServiceInterface groupService = new GroupService(groupRepository);
        Group group = mockGroups.get(0);

        when(groupRepository.save(any(Group.class))).thenReturn(new Group());
        Boolean result = groupService.addGroup(group);

        assertTrue(result);
        verify(groupRepository).save(group);
    }
    @Test
    public void getGroupTest() {
        GroupServiceInterface groupService = new GroupService(groupRepository);
        when(groupRepository.findAll()).thenReturn(mockGroups);

        List<Group> groups = groupService.getGroups();
        assertEquals(groups, mockGroups);
        verify(groupRepository).findAll();
    }

    @Test
    public void deleteGroupTest() {
        GroupServiceInterface groupService = new GroupService(groupRepository);
        Group group = mockGroups.get(0);

        when(groupRepository.existsById(group.getId())).thenReturn(true);
        ResponseEntity<String> result = groupService.deleteGroup(group.getId());

        assertEquals(result.getStatusCode(), HttpStatus.OK);
        verify(groupRepository).existsById(group.getId());
    }

    @Test
    public void updateGroupTest() {
        GroupServiceInterface groupService = new GroupService(groupRepository);
        Group group = mockGroups.get(0);

        Group listafterupdate = new Group(1L,"newgroup","newdesc");

        when(groupRepository.findById(group.getId())).thenReturn(Optional.of(group));

        Group listUpdated = groupService.updateGroup(group.getId(), "newgroup", "newdesc");

        assertEquals(listUpdated, listafterupdate);

        verify(groupRepository).findById(group.getId());
    }
}
