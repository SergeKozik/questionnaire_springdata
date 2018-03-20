package by.kozik.quest.service.impl.dozer;

import by.kozik.quest.dao.UserDao;
import by.kozik.quest.entity.UserEntity;
import org.dozer.DozerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Serge on 17.03.2017.
 */
@Service
public class UserToIdConverter extends DozerConverter<UserEntity,Integer> {

    @Autowired
    private UserDao userDao;

    public UserToIdConverter() {
        super(UserEntity.class, Integer.class);
    }

    @Override
    public Integer convertTo(UserEntity userEntity, Integer integer) {
        return userEntity.getId();
    }

    @Override
    public UserEntity convertFrom(Integer integer, UserEntity userEntity) {
        return userDao.findOne(integer);
    }
}
