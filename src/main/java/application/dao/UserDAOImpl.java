package application.dao;

import application.entity.Role;
import application.entity.User;
import application.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    RoleService roleService;

    @Override
    public List<User> getAllUsers() {

        System.out.println("Соединение с базой успешно выполнено");
        List<User> allUsers = entityManager.createQuery("from  User", User.class).getResultList();
        System.out.println("Передача данных закончена");
        return allUsers;
    }

    @Override
    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void addUser(User user) {
        System.out.println("Соединение с базой успешно выполнено для добавления юзера");
        Set<Role> roles = roleService.getAllRoles().stream().collect(Collectors.toSet());
        user.setRoles(roles);
        entityManager.persist(user);
        System.out.println("Юзер " + user.getName() + " добавлен в БД");
    }

    @Override
    public void updateUser(int id, User updateUser) {
        System.out.println("Соединение с базой успешно выполнено для обновления юзера");
        User userToBeUpdate = getUser(id);
        entityManager.merge(updateUser);
        System.out.println("Юзер " + userToBeUpdate.getName() + " изменен в БД");
    }

    @Override
    public void deleteUser(int id) {
        System.out.println("Соединение с базой успешно выполнено для удаления юзера");
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
        System.out.println("Юзер удален из БД");
    }

    @Override
    public User getByUserName(String name) {

        List<User> userList = entityManager.createQuery("from User where name = :username" , User.class)
                .setParameter("username", name)
                .getResultList();

        return userList.isEmpty() ? null : userList.get(0) ;
    }
}




