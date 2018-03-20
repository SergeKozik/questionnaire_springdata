package by.kozik.quest.dao;

import by.kozik.quest.entity.RoleEntity;
import by.kozik.quest.projection.RoleNameProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by Serge on 09.02.2017.
 */
@Repository
public interface RoleDao extends PagingAndSortingRepository<RoleEntity,Integer> {

    RoleEntity findByRoleName(String roleName);

    Collection<RoleNameProjection> findAllProjectedBy();

    @Query(value = "SELECT r.id FROM RoleEntity r WHERE LOWER(r.roleName) = LOWER(:roleName)")
    Integer returnIdByRoleName(@Param(value = "roleName") String roleName);

}
