package web.dao;

import web.model.Role;

import java.util.List;

public interface RoleDao {
    List<Role> getRoleList();
    List<String> getRoleNamesToList();
    Role getRoleByRoleName(String roleName);
}
