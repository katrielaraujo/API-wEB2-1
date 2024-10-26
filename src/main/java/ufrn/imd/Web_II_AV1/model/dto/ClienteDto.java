package ufrn.imd.Web_II_AV1.model.dto;

import ufrn.imd.Web_II_AV1.model.GeneroPessoa;

public record ClienteDto(
        String nome,
        String cpf,
        GeneroPessoa genero
) {}
