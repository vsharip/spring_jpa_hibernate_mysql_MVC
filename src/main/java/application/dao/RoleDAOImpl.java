package application.dao;

import application.entity.Role;
import application.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        List<Role> userRoles = entityManager.createQuery("from Role", Role.class).getResultList();
        return userRoles;
    }

    @Override
    public Role getRoleByName(String roleName) {
        List<Role> resultList = entityManager.createQuery("from Role where name = :roleName", Role.class)
                .setParameter("roleName", roleName)
                .getResultList();

        return resultList.isEmpty() ? null : resultList.get(0);
    }
}
