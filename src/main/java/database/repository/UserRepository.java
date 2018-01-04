package database.repository;

import database.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByLogin(String login);

    UserEntity findByIdAndLogin(Long id, String login);
}
