package com.lasershop.utils;

import com.lasershop.dtos.request.endereco.EnderecoRequestDTO;
import com.lasershop.dtos.request.login.UsuarioRequestDTO;
import com.lasershop.dtos.request.produto.ProdutoRequestDTO;
import com.lasershop.dtos.response.login.UsuarioResponseDTO;
import com.lasershop.dtos.response.produto.ProdutoResponseDTO;
import com.lasershop.models.endereco.Endereco;
import com.lasershop.models.login.Usuario;
import com.lasershop.models.pedido.ProdutoPedido;
import com.lasershop.models.produto.Produto;
import com.lasershop.models.requisicao.Erro;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

public class Utils {

    public static String encriptarStringSHA256(String str) throws NoSuchAlgorithmException {
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte[] messageDigest = algorithm.digest(str.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        return hexString.toString();
    }

    public static Usuario converterUsuarioRequest(UsuarioRequestDTO usuarioRequestDTO, Endereco endereco){
        ModelMapper modelMapper = new ModelMapper();
        Usuario usuario = modelMapper.map(usuarioRequestDTO, Usuario.class);
        usuario.setEndereco(endereco);
        return usuario;
    }

    public static List<UsuarioResponseDTO> converterUsuarioListToResponse(List<Usuario> usuarios) {
        return new ModelMapper().map(usuarios, new TypeToken<List<UsuarioResponseDTO>>() {}.getType());
    }

    public static List<ProdutoResponseDTO> converterProdutoListToResponse(List<Produto> produtos) {
        return new ModelMapper().map(produtos, new TypeToken<List<ProdutoResponseDTO>>() {}.getType());
    }

    public static UsuarioResponseDTO converterUsuarioToResponse(Usuario usuario) {
        return new ModelMapper().map(usuario, UsuarioResponseDTO.class);
    }

    public static ProdutoResponseDTO converterProdutoRequestToResponse(Produto produto) {
        return new ModelMapper().map(produto, ProdutoResponseDTO.class);
    }

    public static Endereco converterEnderecoRequestToBase(EnderecoRequestDTO enderecoRequestDTO){
        return new ModelMapper().map(enderecoRequestDTO, Endereco.class);
    }

    public static Erro gerarErro(String... msgErro){
        Erro erro = new Erro();
        Arrays.stream(msgErro).forEach(s -> erro.getErros().add(s));
        return erro;
    }

    public static Produto converterProdutoRequestToBase(ProdutoRequestDTO produtoRequestDTO) {
        return new ModelMapper().map(produtoRequestDTO, Produto.class);
    }


    public static ProdutoPedido converterProdutoToProdutoPedido(Produto produto) {
        return new ModelMapper().map(produto, ProdutoPedido.class);
    }
}
