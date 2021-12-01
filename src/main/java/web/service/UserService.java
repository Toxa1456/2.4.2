package web.service;

import web.model.User;
import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();
    void removedUserById(long id);
    User getUserById(long id);
    void changeUser(User user);
    User getUserByName(String name);
}
