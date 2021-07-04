package application.service;

import application.entity.Role;

import java.util.List;

public interface RoleService {
    public List<Role> getAllRoles();
    public Role getRoleByName(String roleName);
    public Role getRoleById(int id);
}
