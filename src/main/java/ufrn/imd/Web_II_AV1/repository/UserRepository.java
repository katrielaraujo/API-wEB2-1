package ufrn.imd.Web_II_AV1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ufrn.imd.Web_II_AV1.model.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
