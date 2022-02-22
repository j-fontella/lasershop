package com.lasershop.dtos.response.login;

import com.lasershop.models.endereco.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode()
public class UsuarioResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private Endereco endereco;
    private BigDecimal limiteCredito;
    private Integer limiteParcelas;
}
