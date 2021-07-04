package application.dao;

import application.entity.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;


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
        entityManager.remove(entityManager.find(User.class, id));
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




