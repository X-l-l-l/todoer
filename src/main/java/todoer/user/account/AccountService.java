package todoer.user.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todoer.user.User;
import todoer.user.UserRepository;

import java.util.Optional;

/**
 * Implementation of the account functionalities
 */
@Service
public class AccountService {
    private UserRepository userRepository;

    @Autowired
    public AccountService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Checks if the username and email of the user don't already exist
     * Creates a new user in the database
     * @param user the user that will be logged in
     */
    public void register(User user) {
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());
        Optional<User> userByUsername = userRepository.findUserByUsername(user.getUsername());

        if (userByEmail.isPresent())
        {
            throw new IllegalStateException("email already exists");
        }
        if (userByUsername.isPresent())
        {
            throw new IllegalStateException("username already exists");
        }
        userRepository.save(user);
    }

    /**
     * Checks if the username is in the database
     * Chacks if the password given and the one of the user already in the database mach
     * @param user the user that will be logged in
     * @return true or false depending on credentials
     */
    public Boolean logIn(User user) {
        Optional<User> userByUsername = userRepository.findUserByUsername(user.getUsername());

        return userByUsername.filter(value -> user.getPassword().equals(value.getPassword())).isPresent();
    }
}
