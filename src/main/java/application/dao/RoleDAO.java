package application.dao;

import application.entity.Role;

import java.util.List;

public interface RoleDAO {
    public List<Role> getAllRoles();
    public Role getRoleByName(String roleName);
    public Role getRoleById(int id);
}
