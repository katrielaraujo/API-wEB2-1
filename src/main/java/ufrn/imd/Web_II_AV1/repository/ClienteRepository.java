package ufrn.imd.Web_II_AV1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ufrn.imd.Web_II_AV1.model.ClienteEntity;
import ufrn.imd.Web_II_AV1.model.ProdutoEntity;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity,Long> {
    List<ClienteEntity> findByAtivoTrue();
}
