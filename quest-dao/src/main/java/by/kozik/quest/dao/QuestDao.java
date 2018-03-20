package by.kozik.quest.dao;

import by.kozik.quest.entity.QuestEntity;
import by.kozik.quest.entity.QuestionEntity;
import by.kozik.quest.projection.QuestCategoryProjection;
import by.kozik.quest.projection.QuestTitleProjection;
import by.kozik.quest.projection.QuestTypeProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by Serge on 28.02.2017.
 */
@Repository
public interface QuestDao extends PagingAndSortingRepository<QuestEntity,Integer> {

    List<QuestEntity> findDistinctByLanguage(String language);
    List<QuestCategoryProjection> findCategoryDistinctAllProjectedBy();
    List<QuestTypeProjection> findTypeDistinctAllProjectedBy();
    List<QuestEntity> findByLanguageInAndCategoryInAndTypeIn(Collection<String> languages, Collection<String> categories,Collection<String> types);

    @Query(value = "SELECT r.id FROM RoleEntity r WHERE LOWER(r.roleName) = LOWER(:roleName)")
    List<QuestionEntity> returnQuestionsFromQuest(int questId);

    QuestTitleProjection findById(int id);
}
