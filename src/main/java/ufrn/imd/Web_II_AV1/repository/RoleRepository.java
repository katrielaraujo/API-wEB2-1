package ufrn.imd.Web_II_AV1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import ufrn.imd.Web_II_AV1.model.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
    Optional<RoleEntity> findByName(String name);
}
