package ufrn.imd.Web_II_AV1.model.dto;

import ufrn.imd.Web_II_AV1.model.Genero;

import java.time.LocalDate;

public record ProdutoDto(
        String nomeProduto,
        String marca,
        LocalDate dataFabricacao,
        LocalDate dataValidade,
        String lote,
        Genero genero,
        boolean ativo
) {}
