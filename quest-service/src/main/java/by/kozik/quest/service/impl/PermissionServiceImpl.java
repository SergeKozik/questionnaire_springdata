package by.kozik.quest.service.impl;

//import by.kozik.quest.bean.FormActionBean;
import by.kozik.quest.bean.FormActionBean;
import by.kozik.quest.dao.PermissionDao;
import by.kozik.quest.dao.RoleDao;
import by.kozik.quest.entity.PermissionEntity;
import by.kozik.quest.projection.PermissionNameProjection;
import by.kozik.quest.entity.RoleEntity;
import by.kozik.quest.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Serge on 09.02.2017.
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<FormActionBean> returnNameListForJS(String roleName) {
        RoleEntity role = roleDao.findByRoleName(roleName);
        List<PermissionEntity> permissionsAll = (List<PermissionEntity>)permissionDao.findAll();

        List<PermissionEntity> rolePermissions = role.getPermissions();
        List<FormActionBean> result = new ArrayList<FormActionBean>();
        for (PermissionEntity permission:permissionsAll) {
            FormActionBean tmpBean = new FormActionBean();
            tmpBean.setButtonCaption(permission.getPermissionName());
            if (rolePermissions.contains(permission)) {
                tmpBean.setButtonLink("checked");
            } else {
                tmpBean.setButtonLink("");
            }
            result.add(tmpBean);
        }
        return result;
    }

    @Override
    public List<FormActionBean> returnNameListForJS() {
        List<PermissionEntity> permissionsAll = (List<PermissionEntity>)permissionDao.findAll();
        List<FormActionBean> result = new ArrayList<FormActionBean>();
        for (PermissionEntity permission:permissionsAll) {
            FormActionBean tmpBean = new FormActionBean();
            tmpBean.setButtonCaption(permission.getPermissionName());
            tmpBean.setButtonLink("");
            result.add(tmpBean);
        }
        return result;
    }

    @Override
    public List<String> returnAllNames() {
        List<String> result = new ArrayList<String>();
        Collection<PermissionNameProjection> projections = permissionDao.findAllProjectedBy();
        if (projections==null) {
            return result;
        }
        for (PermissionNameProjection projection:projections) {
            result.add(projection.getPermissionName());
        }
        return result;
    }
}
