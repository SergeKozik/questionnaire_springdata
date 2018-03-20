package by.kozik.quest.service.impl.dozer;

import by.kozik.quest.dao.RoleDao;
import by.kozik.quest.entity.RoleEntity;
import org.dozer.DozerConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serge on 12.02.2017.
 */
public class RolesToRoleNameConverter extends DozerConverter<List,String> {

    @Autowired
    private RoleDao roleDao;

    public RolesToRoleNameConverter() {
        super(List.class, String.class);
    }

    @Override
    public String convertTo(List list, String s) {
        if ((list==null)||(list.isEmpty())) {
            return "";
        }
        Object object = list.get(0);
        RoleEntity roleEntity = (RoleEntity)object;
        return roleEntity.getRoleName();
    }

    @Override
    public List convertFrom(String s, List list) {
        ArrayList<RoleEntity> result = new ArrayList<RoleEntity>();
        if ((s==null)||("".equals(s))) {
            return result;
        }
        RoleEntity roleEntity= roleDao.findByRoleName(s);
        if (roleEntity!=null) {
            result.add(roleEntity);
        }
        return result;
    }
}
