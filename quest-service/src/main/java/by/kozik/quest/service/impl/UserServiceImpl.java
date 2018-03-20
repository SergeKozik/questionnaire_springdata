package by.kozik.quest.service.impl;

import by.kozik.quest.bean.*;
import by.kozik.quest.dao.PermissionDao;
import by.kozik.quest.dao.RoleDao;
import by.kozik.quest.dao.UserDao;
import by.kozik.quest.entity.PermissionEntity;
import by.kozik.quest.entity.RoleEntity;
import by.kozik.quest.entity.UserEntity;
import by.kozik.quest.service.EntityBeanConverter;
import by.kozik.quest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Serge on 11.02.2017.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private EntityBeanConverter converter;

    @Override
    public UserBeanForUserService findByEmail(String email) {
        UserEntity userEntity = userDao.findByEmail(email);
        UserBeanForUserService bean = converter.convertToBean(userEntity,UserBeanForUserService.class);
        return bean;
    }

    @Override
    public UserBeanRegister register(UserBeanRegister user) {
        UserEntity existUser = userDao.findByEmail(user.getEmail());
        if ((existUser==null)||("".equals(existUser.getEmail()))) {
            UserEntity newUser = converter.convertToEntity(user,UserEntity.class);
            existUser = userDao.save(newUser);
            UserBeanRegister result = converter.convertToBean(existUser,UserBeanRegister.class);
            return result;
        }
        return null;
    }

    @Override
    public List<UserBeanUserList> returnAllEnabled(String editLink, String editCaption, String deleteLink, String deleteCaption, Collection<String> currentPermissions) {
        List<UserEntity> userEntities = userDao.findByEnabled(true);
        List<UserBeanUserList> result = new ArrayList<>();
        if (userEntities==null) {
            return result;
        }
        for (UserEntity entity:userEntities) {
            UserBeanUserList tmpBean= converter.convertToBean(entity,UserBeanUserList.class);
            if (currentPermissions.contains("admin-users-edit")) {
                tmpBean.getButtons().add(new FormActionBean(editLink,editCaption));
            }
            if (currentPermissions.contains("admin-users-delete")) {
                tmpBean.getButtons().add(new FormActionBean(deleteLink,deleteCaption));
            }
            result.add(tmpBean);
        }
        return result;
    }

    @Override
    public List<UserBeanUserList> returnAllDisabled(String restoreLink, String restoreCaption, Collection<String> currentPermissions) {
        List<UserEntity> userEntities = userDao.findByEnabled(false);
        List<UserBeanUserList> result = new ArrayList<>();
        if (userEntities==null) {
            return result;
        }
        for (UserEntity entity:userEntities) {
            UserBeanUserList tmpBean= converter.convertToBean(entity,UserBeanUserList.class);
            if (currentPermissions.contains("admin-users-restore")) {
                tmpBean.getButtons().add(new FormActionBean(restoreLink,restoreCaption));
            }
            result.add(tmpBean);
        }
        return result;
    }

    @Override
    public void disableUserById(int id, Collection<String> currentPermissions) {
        if (currentPermissions.contains("admin-users-delete")) {
            userDao.updateEnabled(false,id);
            //userDao.updateEnabled(id);
            //userDao.updateEnabled(id,"pupkin");
        }
    }

    @Override
    public void enableUserById(int id, Collection<String> currentPermissions) {
        if (currentPermissions.contains("admin-users-restore")) {
            userDao.updateEnabled(true,id);
            //userDao.updateEnabled(id);
            //userDao.updateEnabled(id,"pupkin");
        }
    }

    @Override
    public Collection<String> AuthoritiesToPermissions(Collection<SimpleGrantedAuthority> authorities) {
        Set<String> permissions = new HashSet<>();
        if (authorities!=null) {
            for (GrantedAuthority authority:authorities) {
                permissions.add(authority.getAuthority());
            }
        }
        return permissions;
    }


    @Override
    public UserProfileBean returnUserProfileBean(String userEmail) {
        UserEntity userEntity = userDao.findByEmail(userEmail);
        UserProfileBean result = new UserProfileBean();
        result.setUsername(userEntity.getLogin());
        result.setEmail(userEntity.getEmail());
        result.setRoleName(userEntity.getRoles().get(0).getRoleName());
        result.setId(userEntity.getId());
        List<String> permissionNames = new ArrayList<>();
        for (PermissionEntity permissionEntity:userEntity.getRoles().get(0).getPermissions()) {
            permissionNames.add(permissionEntity.getPermissionName());
        }
        result.setPermissionNames(permissionNames);
        return result;
    }
}
