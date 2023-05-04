package todoer.serviceInterfaces;

import todoer.user.User;

import java.util.Optional;

public interface AccountServiceInterface {

    public Boolean register(User user);

    public Boolean logIn(String username, String password);

}