package todoer.user.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import todoer.user.User;

/**
 * Contains the functionalities of creating and using an account
 */
@RestController
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * @param username the username given
     * @param password the password given
     */
    @PostMapping(path = "login")
    public void logIn(@RequestBody String username, String password) {
        accountService.logIn(username, password);
    }

    /**
     * @param user the user that will be registered
     */
    @PostMapping (path = "register")
    public void register(@RequestBody User user) {
        accountService.register(user);
    }

}
