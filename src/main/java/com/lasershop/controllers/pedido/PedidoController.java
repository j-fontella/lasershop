package com.lasershop.controllers.pedido;

import com.lasershop.dtos.request.login.UsuarioRequestDTO;
import com.lasershop.dtos.request.pedido.PedidoRequestDTO;
import com.lasershop.services.pedido.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/pedidos")
@Validated
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarPedido(@RequestBody @Valid PedidoRequestDTO pedidoRequestDTO) {
        return pedidoService.registrarPedido(pedidoRequestDTO);
    }

}
