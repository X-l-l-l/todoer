package todoer;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import todoer.user.User;
import todoer.user.UserRepository;
import todoer.user.account.AccountService;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AccountTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AccountService accountService;


    @Test
    public void registerUserTest() {
        User user = new User(1L, "Rares", LocalDate.of(2001, Month.AUGUST,13), "rares@mail.com", "rares", "rares");

        Boolean result = accountService.register(user);

        assertTrue(result);
    }

    @Test
    public void loginUserTest() throws Exception{
        User user = new User(1L, "Rares", LocalDate.of(2001, Month.AUGUST,13), "rares@mail.com", "rares", "rares");
        when(userRepository.findUserByUsername("rares")).thenReturn(Optional.of(user));
        Boolean result = accountService.logIn("rares", "rares");
        assertTrue(result);
        verify(userRepository).findUserByUsername("rares");

    }
}
