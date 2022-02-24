package com.lasershop.dtos.response.pedido;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode()
public class PedidoResponseDTO {
    private Long id;
    private String cliente;
    private Integer qtd;
    private BigDecimal total;
}
