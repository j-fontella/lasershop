package com.lasershop.domains;

import lombok.Getter;

@Getter
public enum Erros {

    USUARIO_NAO_ENCONTRADO("Este usuário não foi encontrado no sistema."),
    USUARIO_JA_CADASTRADO("Este usuário já está cadastrado no sistema"),
    MENSAGEM_ERRO_DEFAULT("Um erro ocorreu nesta operação, recarregue a página, tente novamente e caso persista contate o suporte.");


    private final String descricao;

    Erros(String descricao) {
        this.descricao = descricao;
    }
}
