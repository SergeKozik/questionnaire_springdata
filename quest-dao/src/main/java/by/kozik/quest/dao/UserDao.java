package by.kozik.quest.dao;

import by.kozik.quest.entity.UserEntity;
import by.kozik.quest.projection.UserNameProjection;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Serge on 11.02.2017.
 */

@Repository
public interface UserDao extends PagingAndSortingRepository<UserEntity,Integer> {

    UserEntity findByEmail(String email);

    List<UserEntity> findByEnabled(boolean enabled);

    @Modifying(clearAutomatically = true)
    @Query(value = "update user u set u.enabled = :flag where u.id = :id", nativeQuery = true)
    void updateEnabled(@Param(value = "flag") boolean flag, @Param(value = "id") Integer id);
    //void updateEnabled(@Param(value = "flag")boolean flag, @Param(value = "userId")int userId);

    UserNameProjection findById(int id);

}
