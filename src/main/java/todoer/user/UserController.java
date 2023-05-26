package todoer.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import todoer.serviceInterfaces.UserServiceInterface;

import java.time.LocalDate;
import java.util.List;

/**
 * Contains all the functional methods of a user
 */
@RestController
@RequestMapping("users")
public class UserController {

    private final UserServiceInterface userService;

    @Autowired
    public UserController(UserServiceInterface userService) {
        this.userService = userService;
    }

    /**
     * @return a list of all users in the database
     */
    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "id/{userId}")
    public User getUserById(Long userId){
        return userService.getUserById(userId);
    }

    @CrossOrigin
    @GetMapping(path = "{username}")
    public User getUserByUsername(@PathVariable("username") String username){
        return userService.getUserByUsername(username);
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
    @CrossOrigin
    @PutMapping(path = "{userId}")
    public void updateUser(
            @PathVariable("userId") Long userId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) LocalDate date
            )
    {
        userService.updateUser(userId, name, email, password, date);
    }

}
