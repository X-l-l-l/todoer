package todoer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@SpringBootApplication
public class TodoerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TodoerApplication.class, args);
    }

}
