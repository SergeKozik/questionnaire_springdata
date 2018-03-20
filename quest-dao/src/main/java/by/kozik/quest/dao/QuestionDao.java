package by.kozik.quest.dao;

import by.kozik.quest.entity.QuestionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Serge on 24.03.2017.
 */
@Repository
public interface QuestionDao extends PagingAndSortingRepository<QuestionEntity,Integer> {

    @Query("FROM QuestionEntity q WHERE q.quest.id=:questId")
    public List<QuestionEntity> returnQuestionsFromQuest(@Param("questId") int questId);

}
