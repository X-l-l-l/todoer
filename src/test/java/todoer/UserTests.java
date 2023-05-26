package todoer;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import todoer.serviceInterfaces.UserServiceInterface;
import todoer.user.User;
import todoer.user.UserRepository;
import todoer.user.UserService;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserTests {

    @Mock
    private UserRepository userRepository;

    List<User> mockUsers = Arrays.asList(new User(1L, "Rares", LocalDate.of(2001, Month.AUGUST, 13), "rares@mail.com", "rares", "rares"), new User(2L, "Raress", LocalDate.of(2002, Month.AUGUST, 13), "raress@mail.com", "raress", "raress"));


    @Test
    public void getUserTest() {
        UserServiceInterface userService = new UserService(userRepository);
        when(userRepository.findAll()).thenReturn(mockUsers);

        List<User> users = userService.getUsers();
        assertEquals(users, mockUsers);
        verify(userRepository).findAll();
    }

    @Test
    public void deleteUserTest() {
        UserServiceInterface userService = new UserService(userRepository);
        User user = new User(1L, "Rares", LocalDate.of(2001, Month.AUGUST, 13), "rares@mail.com", "rares", "rares");

        when(userRepository.existsById(user.getId())).thenReturn(true);
        ResponseEntity<String> result = userService.deleteUser(user.getId());

        assertEquals(result.getStatusCode(), HttpStatus.OK);
        verify(userRepository).existsById(user.getId());
    }

    @Test
    public void updateUserTest() {
        UserServiceInterface userService = new UserService(userRepository);
        User user = new User(1L, "Rares", LocalDate.of(2001, Month.AUGUST, 13), "rares@mail.com", "rares", "rares");

        User userafterupdate = new User(1L, "Raressss", LocalDate.of(2001, Month.AUGUST, 13), "newemail@gmail.com", "rares", "rares");

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        User userUpdated = userService.updateUser(user.getId(), "Raressss", "newemail@gmail.com", "rares", LocalDate.of(2001, Month.AUGUST, 13));

        assertEquals(userUpdated, userafterupdate);

        verify(userRepository).findById(user.getId());
    }

}
