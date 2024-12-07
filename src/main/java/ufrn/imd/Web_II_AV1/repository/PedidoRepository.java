package ufrn.imd.Web_II_AV1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ufrn.imd.Web_II_AV1.model.PedidoEntity;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<PedidoEntity,Long> {
    List<PedidoEntity> findAllByAtivoTrue();
    Optional<PedidoEntity> findByIdAndAtivoTrue(Long id);
}
