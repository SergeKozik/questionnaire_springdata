package by.kozik.quest.service;

import by.kozik.quest.bean.RoleBeanFromForm;
import by.kozik.quest.entity.RoleEntity;

import java.util.List;
import java.util.Set;

/**
 * Created by Serge on 11.02.2017.
 */
public interface RoleService {

    public List<String> findAllRoleNames();
    public boolean saveRole(RoleBeanFromForm roleBeanFromForm);
    public void deleteRole(String roleName);
    public Set<String> rolesToPermissions(List<RoleEntity> roles);
}
