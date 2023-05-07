package todoer.user.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todoer.serviceInterfaces.AccountServiceInterface;
import todoer.user.User;
import todoer.user.UserRepository;

import java.util.Optional;

/**
 * Implementation of the account functionalities
 */
@Service
public class AccountService implements AccountServiceInterface {
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
    public Boolean register(User user) {
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());
        Optional<User> userByUsername = userRepository.findUserByUsername(user.getUsername());

        if (userByEmail.isPresent())
        {
            throw new IllegalStateException("email already exists");
        }
        if (userByUsername.isPresent()) {
            throw new IllegalStateException("username already exists");
        }
        userRepository.save(user);
        return true;

    }

    /**
     * Checks if the username is in the database
     * Chacks if the password given and the one of the user already in the database mach
     * @param username the username given
     * @param password the password given
     * @return true or false depending on credentials
     */
    public Boolean logIn(String username, String password) {
        Optional<User> userByUsername = userRepository.findUserByUsername(username);

        return userByUsername.filter(value -> password.equals(value.getPassword())).isPresent();
    }
}
