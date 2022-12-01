package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    List<User> allUsers();
    void addUser(User user);
    void changeUser(long id, User updatedUser);
    void deleteUser(long id);
    User getUserById(long id);


}
