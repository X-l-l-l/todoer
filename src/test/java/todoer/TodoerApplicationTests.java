package todoer;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import todoer.user.User;
import todoer.user.UserController;
import todoer.user.UserRepository;
import todoer.user.UserService;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
class TodoerApplicationTests {

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllRecords_success() throws Exception {
        User user1 = new User("ana2", LocalDate.of(2016, Month.JANUARY, 1),"adh","deh","deh");
        User user2 = new User("ana", LocalDate.of(2014, Month.JANUARY, 1),"ad","de","de");

        List<User> users = new ArrayList<>(Arrays.asList(
                user1,user2
        ));

        when(userService.getUsers()).thenReturn(users);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(users.size()))
                .andDo(print());

    }
}
