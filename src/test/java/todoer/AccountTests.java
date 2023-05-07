package todoer;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import todoer.serviceInterfaces.AccountServiceInterface;
import todoer.user.User;
import todoer.user.UserRepository;
import todoer.user.account.AccountService;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AccountTests {

    @Mock
    private UserRepository userRepository;

    @Test
    public void registerUserTest() {
        AccountServiceInterface accountService = new AccountService(userRepository);
        User user = new User("Rares", LocalDate.of(2001, Month.AUGUST,13), "rares@mail.com", "rares", "rares");

        when(userRepository.save(any(User.class))).thenReturn(new User());
        Boolean result = accountService.register(user);

        assertTrue(result);
        verify(userRepository).save(user);
    }

    @Test
    public void loginUserTest() throws Exception{
        AccountServiceInterface accountService = new AccountService(userRepository);

        User user = new User(1L, "Rares", LocalDate.of(2001, Month.AUGUST,13), "rares@mail.com", "rares", "rares");
        when(userRepository.findUserByUsername("rares")).thenReturn(Optional.of(user));
        Boolean result = accountService.logIn("rares", "rares");
        assertTrue(result);
        verify(userRepository).findUserByUsername("rares");

    }
}
