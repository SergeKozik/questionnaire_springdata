package by.kozik.quest.service.impl.dozer;

import by.kozik.quest.dao.PermissionDao;
import by.kozik.quest.entity.PermissionEntity;
import org.dozer.DozerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serge on 14.02.2017.
 */
@Service
public class PermissionsToNamesConverter extends DozerConverter<List,String[]> {

    @Autowired
    private PermissionDao permissionDao;

    public PermissionsToNamesConverter() {
        super(List.class, String[].class);
    }

    @Override
    public String[] convertTo(List list, String[] strings) {
        if ((list==null)||list.isEmpty()) return null;
        String[] result = new String[list.size()];
        for (int i=0;i<list.size();i++) {
            PermissionEntity entity = (PermissionEntity)list.get(i);
            result[i]=entity.getPermissionName();
        }
        return result;
    }

    @Override
    public List convertFrom(String[] strings, List list) {
        List<PermissionEntity> result = new ArrayList<PermissionEntity>();
        if ((strings==null)||(strings.length==0)) {
            return result;
        }
        for (String name:strings) {
            PermissionEntity entity = permissionDao.findByPermissionName(name);
            result.add(entity);
        }
        return result;
    }
}
