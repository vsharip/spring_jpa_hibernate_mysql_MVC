package application.dao;

import application.entity.User;

import java.util.List;


public interface UserDAO {

    public List<User> getAllUsers();

    public void addUser(User user);

    public User getUser(int id);

    public void updateUser(int id, User user);

    public void deleteUser(int id);

    public User getByUserName(String name);

}
