package com.lasershop.repositorys.produtos;

import com.lasershop.models.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findProdutoByNome(String nome);



}
