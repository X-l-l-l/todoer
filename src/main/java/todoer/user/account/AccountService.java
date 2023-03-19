package todoer.user.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todoer.user.User;
import todoer.user.UserRepository;

import java.util.Optional;

@Service
public class AccountService {
    private UserRepository userRepository;

    @Autowired
    public AccountService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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

    public Boolean logIn(User user) {
        Optional<User> userByUsername = userRepository.findUserByUsername(user.getUsername());

        if(userByUsername.isPresent())
        {
            if (user.getPassword().equals(userByUsername.get().getPassword()))
                return true;
        }
        return false;
    }
}
