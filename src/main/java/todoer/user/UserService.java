package todoer.user;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todoer.serviceInterfaces.UserServiceInterface;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Implementation of all the functionalities of a user
 */
@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @return a list of all users in the database
     */
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * @param userId the id by which the user is deleted
     */
    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if(!exists){
            throw new IllegalStateException(
                    "user with id " + userId + " does not exist"
            );
        }
        userRepository.deleteById(userId);
    }

    /**
     * @param userId the id of the user that will be updated
     * @param name the new name
     * @param email the new email
     */
    @Transactional
    public void updateUser(Long userId, String name, String email) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("user with id "+userId+" does not exist"));
        if(name != null && name.length()>0 && !Objects.equals(user.getName(), name)){
            user.setName(name);
        }

        if(email != null && email.length()>0 && !Objects.equals(user.getEmail(), email)){
            Optional<User> userOptional = userRepository.findUserByEmail(email);
            if(userOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            user.setEmail(email);
        }
    }
}
