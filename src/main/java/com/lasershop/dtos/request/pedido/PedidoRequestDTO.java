package com.lasershop.dtos.request.pedido;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode()
public class PedidoRequestDTO {

    @NotNull(message = "Contate o suporte")
    List<Long> idProdutosComprados;

    @NotNull(message = "É obrigatório informar o número de parcelas")
    @Min(value = 1, message = "O número minimo de parelas é 1")
    Integer parcelas;

    @NotNull(message = "É obrigatório informar o usuário comprador")
    Long idUsuario;

}
