package todoer.user.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import todoer.user.User;

@RestController
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(path = "login")
    public void logIn(@RequestBody User user) {
        accountService.logIn(user);
    }

    @PostMapping (path = "register")
    public void register(@RequestBody User user) {
        accountService.register(user);
    }

}
