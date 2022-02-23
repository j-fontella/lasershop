package com.lasershop.dtos.request.produto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode()
public class ProdutoRequestDTO {

    private Long id;

    @NotBlank(message = "O nome do prodúto é obriatório")
    private String nome;

    @NotNull(message = "O valor do prodúto é obriatório")
    private BigDecimal valor;

    @NotBlank(message = "A descrição do prodúto é obriatório")
    private String descricao;
}
