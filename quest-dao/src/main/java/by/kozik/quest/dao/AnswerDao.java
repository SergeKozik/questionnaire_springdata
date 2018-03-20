package by.kozik.quest.dao;

import by.kozik.quest.entity.AnswerEntity;
import by.kozik.quest.entity.PermissionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Serge_Kozik on 3/13/2017.
 */
@Repository
public interface AnswerDao extends PagingAndSortingRepository<AnswerEntity,Integer> {

    @Query(value = "SELECT COUNT(umr.id) FROM user_main_result umr JOIN (SELECT user_main_result_id FROM user_answer WHERE user_answer.answer_variant_id=?0) AS sua ON umr.id=sua.user_main_result_id", nativeQuery = true)
    public int returnNumUsersAnswered(Integer answerId);
}
