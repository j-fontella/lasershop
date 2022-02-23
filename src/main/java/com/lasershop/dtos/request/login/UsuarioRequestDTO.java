package com.lasershop.dtos.request.login;

import com.lasershop.dtos.request.endereco.EnderecoRequestDTO;
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
public class UsuarioRequestDTO {

    private Long id;

    @NotBlank(message = "Nome deve ser preenchido")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres.")
    private String nome;

    @NotBlank(message = "Email deve ser preenchido")
    @Email(message = "Você deve inserir um email válido.")
    private String email;

    @NotBlank(message = "Senha deve ser preenchida")
    @Size(min = 6, max = 20, message = "A senha deve ter entre 6 e 20 caracteres.")
    private String senha;

    @NotNull(message = "Endereço inválido")
    private EnderecoRequestDTO endereco;

    @NotNull(message = "O limite de crédito deve ser informado")
    private BigDecimal limiteCredito;

    @NotNull(message = "O limite de parcelas deve ser informado")
    private Integer limiteParcelas;

}
