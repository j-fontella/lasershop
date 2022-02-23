package com.lasershop.services.pedido;

import com.lasershop.domains.Erros;
import com.lasershop.dtos.request.pedido.PedidoRequestDTO;
import com.lasershop.exceptions.PedidoInvalidoException;
import com.lasershop.models.login.Usuario;
import com.lasershop.models.pedido.Pedido;
import com.lasershop.models.pedido.ProdutoPedido;
import com.lasershop.models.produto.Produto;
import com.lasershop.repositorys.login.UsuarioRepository;
import com.lasershop.repositorys.perdido.PedidoRepository;
import com.lasershop.repositorys.perdido.ProdutoPedidoRepository;
import com.lasershop.repositorys.produtos.ProdutoRepository;
import com.lasershop.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ProdutoPedidoRepository produtoPedidoRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    public ResponseEntity<?> registrarPedido(PedidoRequestDTO pedidoRequestDTO) {

        List<Long> idProdutosPedidos = pedidoRequestDTO.getIdProdutosComprados();
        List<ProdutoPedido> carrinho = new ArrayList<>();
        Usuario usuarioComprador;
        BigDecimal totalCompra;
        BigDecimal desconto = BigDecimal.ZERO;
        Integer numeroParcelas = pedidoRequestDTO.getParcelas();

        try {
            validarHorarioPedido();
            gerarCarrinho(idProdutosPedidos, carrinho);

            totalCompra = BigDecimal.valueOf(carrinho.stream().mapToDouble(value -> value.getValor().doubleValue()).sum());
            usuarioComprador = validarUsuario(pedidoRequestDTO.getIdUsuario());
            validarDadosFinanceirosCliente(usuarioComprador, pedidoRequestDTO, totalCompra);

            if(numeroParcelas == 1){
                desconto = totalCompra.divide(BigDecimal.TEN, 2, RoundingMode.DOWN);
            }

        }catch (PedidoInvalidoException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuarioComprador);
        pedido.setProdutos(carrinho);
        pedido.setValorTotal(totalCompra);
        pedido.setDesconto(desconto);
        pedido.setParcelas(numeroParcelas);
        pedidoRepository.save(pedido);

        return ResponseEntity.ok().build();
    }

    private void validarDadosFinanceirosCliente(Usuario usuarioComprador, PedidoRequestDTO pedido, BigDecimal totalCompra) {
        if(pedido.getParcelas() > usuarioComprador.getLimiteParcelas()){
            throw new PedidoInvalidoException(Erros.NUMERO_PARCELAS_SUPERIOR_LIMITE.getDescricao());
        }
        if(usuarioComprador.getLimiteCredito().compareTo(totalCompra) < 0){
            throw new PedidoInvalidoException(Erros.VALOR_PEDIDO_SUPERIOR_LIMITE_CREDITO.getDescricao());
        }
    }

    private void validarHorarioPedido(){
        LocalDateTime dataAtual = LocalDateTime.now();
        LocalDateTime horaMinimaPermitida = LocalDateTime.now().with(LocalTime.of(8, 0));
        LocalDateTime horaMaximaPermitida = LocalDateTime.now().with(LocalTime.of(18, 0));
        if(dataAtual.isBefore(horaMinimaPermitida) || dataAtual.isAfter(horaMaximaPermitida)){
            throw new PedidoInvalidoException(Erros.PEDIDO_HORARIO_NAO_PERMITIDO.getDescricao());
        }
    }

    private void gerarCarrinho(List<Long> idProdutosPedidos, List<ProdutoPedido> carrinho){
        for (Long id: idProdutosPedidos) {
            Optional<Produto> produto = produtoRepository.findById(id);
            if(produto.isEmpty()){
                throw new PedidoInvalidoException(Erros.PRODUTO_NAO_ENCONTRADO.getDescricao());

            }
            carrinho.add(Utils.converterProdutoToProdutoPedido(produto.get()));
        }
    }

    private Usuario validarUsuario(Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isEmpty()){
            throw new PedidoInvalidoException(Erros.USUARIO_NAO_ENCONTRADO.getDescricao());
        }
        return usuario.get();
    }


}
