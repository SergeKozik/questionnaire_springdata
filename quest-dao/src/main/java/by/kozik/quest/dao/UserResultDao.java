package by.kozik.quest.dao;

import by.kozik.quest.entity.UserMainResultEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Serge_Kozik on 3/13/2017.
 */
@Repository
public interface UserResultDao extends PagingAndSortingRepository<UserMainResultEntity,Integer> {

    List<UserMainResultEntity> findAllByQuest_id(int id);
    List<UserMainResultEntity> findAllByQuest_idAndUser_id(int questId, int userId);
    List<UserMainResultEntity> findAllByUser_id(int userId);

}
