package todoer.user.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todoer.serviceInterfaces.AccountServiceInterface;
import todoer.user.User;

/**
 * Contains the functionalities of creating and using an account
 */
@RestController
public class AccountController {

    private final AccountServiceInterface accountService;

    @Autowired
    public AccountController(AccountServiceInterface accountService) {
        this.accountService = accountService;
    }

    /**
     * @param credentials the credentials given
     * @return response
     */
    @CrossOrigin
    @PostMapping(path = "login")
    public ResponseEntity logIn(@RequestBody String credentials) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        Account account = objectMapper.readValue(credentials,Account.class);

        if(accountService.logIn(account.username, account.password))
        {
            System.out.println("DAAA");
            return new ResponseEntity("Logged in", HttpStatus.OK);
        }
        return new ResponseEntity("Wrong credentials", HttpStatus.UNAUTHORIZED);
    }

    /**
     * @param user the user that will be registered
     */
    @CrossOrigin
    @PostMapping (path = "register")
    public void register(@RequestBody User user) {
        accountService.register(user);
    }

}
