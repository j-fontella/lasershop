package com.lasershop.models.pedido;

import com.lasershop.models.login.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Pedido", schema = "pedido")
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "id_pedido", referencedColumnName = "id")
    private List<ProdutoPedido> produtos;

    @Column
    private BigDecimal valorTotal;

    @Column
    private BigDecimal desconto;

    @Column
    private Integer parcelas;

}
