package by.kozik.quest.dao;

import by.kozik.quest.entity.PermissionEntity;
import by.kozik.quest.projection.PermissionNameProjection;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by Serge on 09.02.2017.
 */
@Repository
public interface PermissionDao extends PagingAndSortingRepository<PermissionEntity,Integer> {

    PermissionEntity findByPermissionName(String permissionName);

    Collection<PermissionNameProjection> findAllProjectedBy();

}
