package by.kozik.quest.service.impl.dozer;

import by.kozik.quest.dao.QuestDao;
import by.kozik.quest.entity.QuestEntity;
import org.dozer.DozerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Serge on 17.03.2017.
 */
@Service
public class QuestToIdConverter extends DozerConverter<QuestEntity,Integer> {

    @Autowired
    private QuestDao questDao;

    public QuestToIdConverter() {
        super(QuestEntity.class, Integer.class);
    }

    @Override
    public Integer convertTo(QuestEntity questEntity, Integer integer) {
        return questEntity.getId();
    }

    @Override
    public QuestEntity convertFrom(Integer integer, QuestEntity questEntity) {
        QuestEntity result = questDao.findOne(integer);
        return result;
    }
}
