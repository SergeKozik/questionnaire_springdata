package by.kozik.quest.service.impl;

import by.kozik.quest.bean.RoleBeanFromForm;
import by.kozik.quest.dao.RoleDao;
import by.kozik.quest.entity.PermissionEntity;
import by.kozik.quest.entity.RoleEntity;
import by.kozik.quest.projection.RoleNameProjection;
import by.kozik.quest.service.EntityBeanConverter;
import by.kozik.quest.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Serge on 11.02.2017.
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private EntityBeanConverter converter;

    @Override
    public List<String> findAllRoleNames() {
        List<String> result = new ArrayList<String>();
        Collection<RoleNameProjection> nameProjections = roleDao.findAllProjectedBy();
        if (nameProjections==null) {
            return result;
        }
        for (RoleNameProjection projection:nameProjections) {
            result.add(projection.getRoleName());
        }
        return result;
    }

    @Override
    public boolean saveRole(RoleBeanFromForm roleBeanFromForm) {
        //RoleEntity storedRole = roleDao.findByRoleName(roleBeanFromForm.getRoleName());
        Integer storedRoleId = roleDao.returnIdByRoleName(roleBeanFromForm.getRoleName());
        RoleEntity roleEntity = converter.convertToEntity(roleBeanFromForm,RoleEntity.class);
        if (storedRoleId!=null) {
            roleEntity.setId(storedRoleId);
        }
        RoleEntity savedEntity = roleDao.save(roleEntity);
        if ((savedEntity!=null)&&(savedEntity.getId()>0)) {
            return true;
        }
        return false;
    }

    @Override
    public void deleteRole(String roleName) {
        RoleEntity roleEntity = roleDao.findByRoleName(roleName);
        if (roleEntity==null) {
            return;
        }
        roleDao.delete(roleEntity);
    }

    @Override
    public Set<String> rolesToPermissions(List<RoleEntity> roles) {
        HashSet<String> result = new HashSet<>();
        for (RoleEntity role:roles) {
            //RoleEntity roleEntity = roleDao.findOne(role.getId());
            for (PermissionEntity permissionEntity:role.getPermissions()) {
                result.add(permissionEntity.getPermissionName());
            }
        }
        return result;
    }
}
