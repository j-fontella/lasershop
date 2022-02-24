package com.lasershop.repositorys.pedido;

import com.lasershop.dtos.response.pedido.PedidoResponseDTO;
import com.lasershop.models.pedido.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @Query(value = "SELECT * FROM pedido.pedido AS ped JOIN pedido.produto_pedido as prod ON prod.id_pedido = ped.id", nativeQuery = true)
    List<Pedido> getPedidosPorCliente();
}
