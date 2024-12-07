package ufrn.imd.Web_II_AV1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ufrn.imd.Web_II_AV1.model.ClienteEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity,Long> {
    List<ClienteEntity> findAllByAtivoTrue();
    Optional<ClienteEntity> findByIdAndAtivoTrue(Long id);
}
