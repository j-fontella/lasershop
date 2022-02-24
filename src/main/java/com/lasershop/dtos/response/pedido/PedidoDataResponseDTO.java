package com.lasershop.dtos.response.pedido;

import com.lasershop.models.pedido.Pedido;
import lombok.Data;

import java.util.Set;

@Data
public class PedidoDataResponseDTO {

    private Set<PedidoResponseDTO> dadosExibicao;
    private Set<Pedido> dadosPedidos;

    public PedidoDataResponseDTO(Set<PedidoResponseDTO> dadosPedidosResponse, Set<Pedido> pedidosUniqueResponse) {
        this.dadosExibicao = dadosPedidosResponse;
        this.dadosPedidos = pedidosUniqueResponse;
    }


}
