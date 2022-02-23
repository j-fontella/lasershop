package com.lasershop.repositorys.perdido;

import com.lasershop.models.pedido.ProdutoPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoPedidoRepository extends JpaRepository<ProdutoPedido, Long> {
}
