package com.algaworks.algafood.domain.model;

public enum StatusPedido {

    AGUARDANDO_PAGAMENTO(1),
    PAGO(2),
    EM_SEPARACAO(3),
    ENVIADO(4),
    ENTREGUE(5),
    CANCELADO(6);

    private final int valor;
    StatusPedido(int valor){
        this.valor = valor;
    }

    public int getValor(StatusPedido statusPedido){
        return statusPedido.valor;
    }

}
