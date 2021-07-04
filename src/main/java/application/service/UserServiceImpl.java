package application.service;

import application.dao.UserDAO;
import application.entity.Role;
import application.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    RoleService roleService;

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    @Transactional
    public User getUser(int id) {
        return userDAO.getUser(id);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        Set<Role> roleSet = new HashSet<>();
        try {
            if (userDAO.getByUserName(user.getName()) == null) {
                user.getRoles().stream().forEach(role -> {
                            if (role.getName().equals("1")) {
                                roleSet.add(roleService.getRoleById(1));
                            } else if (role.getName().equals("2")) {
                                roleSet.add(roleService.getRoleById(2));
                            }
                        }
                );
                user.setRoles(roleSet);
                userDAO.addUser(user);
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    @Transactional
    public void updateUser(int id, User user) {
        Set<Role> roleSet = new HashSet<>();
        user.getRoles().stream().forEach(role -> {
                    if (role.getName().equals("1")) {
                        roleSet.add(roleService.getRoleById(1));
                    } else if (role.getName().equals("2")) {
                        roleSet.add(roleService.getRoleById(2));
                    }
                }
        );
        user.setRoles(roleSet);
        userDAO.updateUser(id, user);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }

    @Override
    @Transactional
    public User getByUserName(String name) {
        return userDAO.getByUserName(name);
    }
}
