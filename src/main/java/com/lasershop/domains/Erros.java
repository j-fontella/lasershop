package com.lasershop.domains;

import lombok.Getter;

@Getter
public enum Erros {

    USUARIO_NAO_ENCONTRADO("Este usuário não foi encontrado no sistema."),
    USUARIO_JA_CADASTRADO("Este usuário já está cadastrado no sistema"),
    PRODUTO_NAO_ENCONTRADO("Este prodúto não foi encontrado no sistema."),
    PRODUTO_JA_CADASTRADO("Este prodúto já está cadastrado no sistema"),
    PEDIDO_HORARIO_NAO_PERMITIDO("Os pedidos só podem ser feitos entre 8:00 e 18:00"),
    NUMERO_PARCELAS_SUPERIOR_LIMITE("O numero de parcelas solicitada para compra é superior ao do cliente"),
    VALOR_PEDIDO_SUPERIOR_LIMITE_CREDITO("O valor da compra é superior ao limite de crédito do cliente"),
    MENSAGEM_ERRO_DEFAULT("Um erro ocorreu nesta operação, recarregue a página, tente novamente e caso persista contate o suporte.");


    private final String descricao;

    Erros(String descricao) {
        this.descricao = descricao;
    }
}
