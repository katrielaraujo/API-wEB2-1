package ufrn.imd.Web_II_AV1.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "cliente")
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;

    @Enumerated(EnumType.STRING)
    private GeneroPessoa genero;
    private boolean ativo = true;
}
