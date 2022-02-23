package com.lasershop.controllers.produto;

import com.lasershop.dtos.request.produto.ProdutoRequestDTO;
import com.lasershop.services.produtos.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/produtos")
@Validated
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarProduto(@RequestBody @Valid ProdutoRequestDTO produtoRequestDTO) {
        return produtoService.registrarProduto(produtoRequestDTO);
    }

    @PutMapping("/editar")
    public ResponseEntity<?> editarProduto(@RequestBody @Valid ProdutoRequestDTO produtoRequestDTO) {
        return produtoService.editarProduto(produtoRequestDTO);
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?> deletar(@RequestParam Long id) {
        return produtoService.deletarProduto(id);
    }

    @GetMapping("/todos")
    public ResponseEntity<?> getTodosProdutos() {
        return produtoService.getTodosProdutos();
    }

    @GetMapping("/porNome")
    public ResponseEntity<?> getProdutoPorNome(@RequestParam String nome) {
        return produtoService.getProdutoPorNome(nome);
    }

    @GetMapping("/porId")
    public ResponseEntity<?> getProdutoPorId(@RequestParam Long id) {
        return produtoService.getProdutoPorId(id);
    }

}
