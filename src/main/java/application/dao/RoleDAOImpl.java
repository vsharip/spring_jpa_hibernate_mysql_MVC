package application.dao;

import application.entity.Role;
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
        return entityManager.createQuery("from  Role", Role.class).getResultList();
    }

    @Override
    public Role getRoleByName(String roleName) {
         return entityManager.find(Role.class, roleName);
    }

    @Override
    public Role getRoleById(int id) {
        return entityManager.find(Role.class, id);
    }
}
