package todoer.serviceInterfaces;

import org.springframework.http.ResponseEntity;
import todoer.user.User;

import java.time.LocalDate;
import java.util.List;

public interface UserServiceInterface {
    public List<User> getUsers();

    public ResponseEntity<String> deleteUser(Long userId);

    public User updateUser(Long userId, String name, String email, String password, LocalDate date);

    User getUserByUsername(String username);

    User getUserById(Long userId);
}
