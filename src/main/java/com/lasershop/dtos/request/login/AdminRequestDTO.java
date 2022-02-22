package com.lasershop.dtos.request.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode()
public class AdminRequestDTO {

    @NotNull(message = "Matricula deve ser preenchida")
    private Long matricula;

    @NotBlank(message = "Senha deve ser preenchida")
    @Size(min = 6, max = 20, message = "A senha deve ter entre 6 e 20 caracteres.")
    private String senha;
}
