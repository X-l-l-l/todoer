package todoer.user;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import todoer.serviceInterfaces.UserServiceInterface;

import java.time.LocalDate;
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
     * @return
     */
    public ResponseEntity<String> deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if(!exists){
            throw new IllegalStateException(
                    "user with id " + userId + " does not exist"
            );
        }
        userRepository.deleteById(userId);
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }

    /**
     * @param userId the id of the user that will be updated
     * @param name   the new name
     * @param email  the new email
     * @return
     */
    @Transactional
    public User updateUser(Long userId, String name, String email, String password, LocalDate date) {
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

        if(password != null && password.length()>0 && !Objects.equals(user.getPassword(), password)) {
            user.setPassword(password);
        }

        if(date != null && !Objects.equals(user.getDateOfBirth(), date)) {
            user.setDateOfBirth(date);
        }

        return user;
    }

    /**
     * @param username username used to find a user in the db
     * @return
     */
    @Override
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username).get();
    }

    /**
     * @param userId id used to find a user in the db
     * @return
     */
    @Override
    public User getUserById(Long userId) {
        return userRepository.getUserById(userId);
    }
}
