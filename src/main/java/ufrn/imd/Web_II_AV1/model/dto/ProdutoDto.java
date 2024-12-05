package ufrn.imd.Web_II_AV1.model.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import ufrn.imd.Web_II_AV1.model.Genero;

import java.time.LocalDate;

public record ProdutoDto(

        @Size(min = 3, max = 50) String nomeProduto,
        @Size(min = 3, max = 20) String marca,
        @PastOrPresent LocalDate dataFabricacao,
        @FutureOrPresent LocalDate dataValidade,
        @Size(min = 5, max = 12) String lote,
        Genero genero,
        boolean ativo
) {}
