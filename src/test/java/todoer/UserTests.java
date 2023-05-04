package todoer;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import todoer.user.User;
import todoer.user.UserController;
import todoer.user.UserService;
import todoer.user.account.AccountService;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = UserController.class)
public class UserTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private AccountService accountService;

    List<User> mockUsers = Arrays.asList(new User(1L, "Rares", LocalDate.of(2001, Month.AUGUST,13), "rares@mail.com", "rares", "rares"), new User(2L, "Raress", LocalDate.of(2002, Month.AUGUST,13), "raress@mail.com", "raress", "raress"));

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getUserTest() throws Exception{
        Mockito.when(userService.getUsers()).thenReturn(mockUsers);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/users").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "[{\"id\":1,\"name\":\"Rares\",\"dateOfBirth\":\"2001-08-13\",\"email\":\"rares@mail.com\",\"username\":\"rares\",\"password\":\"rares\",\"todos\":null},{\"id\":2,\"name\":\"Raress\",\"dateOfBirth\":\"2002-08-13\",\"email\":\"raress@mail.com\",\"username\":\"raress\",\"password\":\"raress\",\"todos\":null}]";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

}
