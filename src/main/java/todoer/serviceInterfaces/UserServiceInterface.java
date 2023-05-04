package todoer.serviceInterfaces;

import jakarta.transaction.Transactional;
import todoer.user.User;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface UserServiceInterface {
    public List<User> getUsers();

    public void deleteUser(Long userId);

    public void updateUser(Long userId, String name, String email);
}
