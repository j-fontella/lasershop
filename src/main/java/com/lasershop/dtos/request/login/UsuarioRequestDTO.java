package com.lasershop.dtos.request.login;

import com.lasershop.dtos.request.endereco.EnderecoRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode()
public class UsuarioRequestDTO {

    private Long id;

    @NotBlank(message = "Nome deve ser preenchido")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres.")
    private String nome;

    @NotNull(message = "Endereço inválido")
    @Valid
    private EnderecoRequestDTO endereco;

    @NotNull(message = "O limite de crédito deve ser informado")
    private BigDecimal limiteCredito;

    @NotNull(message = "O limite de parcelas deve ser informado")
    private Integer limiteParcelas;

}
