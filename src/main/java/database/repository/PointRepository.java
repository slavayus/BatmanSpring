package database.repository;

import database.entity.PointEntity;
import database.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointRepository extends CrudRepository<PointEntity, Long> {
    List<PointEntity> findByZoom(Double zoom);

    List<PointEntity> findByUserEntityAndZoom(UserEntity userEntity, Double zoom);

    List<PointEntity> findByUserEntityId(Long userEntity);
}