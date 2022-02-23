package com.lasershop.models.pedido;

import com.lasershop.models.produto.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ProdutoPedido", schema = "pedido")
@Data
public class ProdutoPedido  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(100)")
    private String nome;

    @Column
    private BigDecimal valor;

    @Column
    private String descricao;

}
