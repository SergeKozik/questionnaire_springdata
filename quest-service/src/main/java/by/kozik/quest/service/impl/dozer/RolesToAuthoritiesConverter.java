package by.kozik.quest.service.impl.dozer;

import by.kozik.quest.bean.PermissionAsAuthority;
import by.kozik.quest.dao.PermissionDao;
import by.kozik.quest.dao.RoleDao;
import by.kozik.quest.entity.PermissionEntity;
import by.kozik.quest.entity.RoleEntity;
import by.kozik.quest.service.RoleService;
import org.dozer.DozerConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Serge on 11.02.2017.
 */

public class RolesToAuthoritiesConverter extends DozerConverter<List,List> {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionDao permissionDao;

    public RolesToAuthoritiesConverter() {
        super(List.class, List.class);
    }

    @Override
    public List convertTo(List source, List destination) {
        List<RoleEntity> roles = (List<RoleEntity>)source;
        Set<String> permissionSet = roleService.rolesToPermissions(roles);
        List<PermissionAsAuthority> result = new ArrayList<PermissionAsAuthority>();
        for (String permissionName:permissionSet) {
            PermissionAsAuthority tmpBean = new PermissionAsAuthority();
            tmpBean.setPermissionName(permissionName);
            result.add(tmpBean);
        }
        return result;
    }

    @Override
    public List convertFrom(List source, List destination) {
        List<RoleEntity> result = new ArrayList<RoleEntity>();
        return result;
    }

}
