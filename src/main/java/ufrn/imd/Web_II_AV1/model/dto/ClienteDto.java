package ufrn.imd.Web_II_AV1.model.dto;

import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;
import ufrn.imd.Web_II_AV1.model.GeneroPessoa;

public record ClienteDto(
        @Size(min = 8, max = 50) String nome,
        @CPF String cpf,
        GeneroPessoa genero
) {}
