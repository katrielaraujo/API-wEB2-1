package ufrn.imd.Web_II_AV1.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "produto")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeProduto;
    private String marca;
    private LocalDate dataFabricacao;
    private LocalDate dataValidade;
    private String lote;
    @Enumerated(EnumType.STRING)
    private Genero genero;
    private boolean ativo = true;
}
