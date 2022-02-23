package com.lasershop.services.produtos;

import com.lasershop.domains.Erros;
import com.lasershop.dtos.request.produto.ProdutoRequestDTO;
import com.lasershop.dtos.response.login.UsuarioResponseDTO;
import com.lasershop.dtos.response.produto.ProdutoResponseDTO;
import com.lasershop.models.login.Usuario;
import com.lasershop.models.produto.Produto;
import com.lasershop.repositorys.produtos.ProdutoRepository;
import com.lasershop.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;


    public ResponseEntity<?> registrarProduto(ProdutoRequestDTO produtoRequestDTO) {
        Optional<Produto> produto = produtoRepository.findProdutoByNome(produtoRequestDTO.getNome());
        if (produto.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Utils.gerarErro(Erros.PRODUTO_JA_CADASTRADO.getDescricao()));
        }
        produtoRepository.save(Utils.converterProdutoRequestToBase(produtoRequestDTO));
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> editarProduto(ProdutoRequestDTO produtoRequestDTO) {
        Optional<Produto> produto = produtoRepository.findById(produtoRequestDTO.getId());
        if (produto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Utils.gerarErro(Erros.PRODUTO_NAO_ENCONTRADO.getDescricao()));
        }
        produtoRepository.save(Utils.converterProdutoRequestToBase(produtoRequestDTO));
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> deletarProduto(Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Utils.gerarErro(Erros.PRODUTO_NAO_ENCONTRADO.getDescricao()));
        }
        produtoRepository.delete(produto.get());
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> getTodosProdutos() {
        return ResponseEntity.ok().body(produtoRepository.findAll());
    }

    public ResponseEntity<?> getProdutoPorId(Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isEmpty()) {
            return ResponseEntity.badRequest().body(Erros.PRODUTO_NAO_ENCONTRADO.getDescricao());
        }
        return ResponseEntity.ok().body(produto.get());
    }

    public ResponseEntity<?> getProdutoPorNome(String nome) {
        Optional<Produto> produto = produtoRepository.findProdutoByNome(nome);
        if (produto.isEmpty()) {
            return ResponseEntity.badRequest().body(Erros.PRODUTO_NAO_ENCONTRADO.getDescricao());
        }
        return ResponseEntity.ok().body(produto.get());
    }


}
