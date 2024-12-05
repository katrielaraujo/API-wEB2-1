package ufrn.imd.Web_II_AV1.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ufrn.imd.Web_II_AV1.model.ClienteEntity;
import ufrn.imd.Web_II_AV1.model.ProdutoEntity;

import java.util.List;

public record PedidoDto(
        @NotBlank  String codigo,
        @NotNull ClienteEntity cliente,
        List<ProdutoEntity> produtos
) {
}
