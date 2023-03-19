package todoer.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contains all the functional methods of a user
 */
@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * @return a list of all users in the database
     */
    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    /**
     * @param userId by which the user in the database, if it exists, is deleted
     */
    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }

    /**
     * @param userId the id of the user that is updated
     * @param name the new name
     * @param email the new email
     */
    @PutMapping(path = "{userId}")
    public void updateUser(
            @PathVariable("userId") Long userId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    )
    {
        userService.updateUser(userId, name, email);
    }
}
