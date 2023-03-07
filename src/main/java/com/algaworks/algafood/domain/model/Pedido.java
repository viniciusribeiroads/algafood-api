package com.algaworks.algafood.domain.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private BigDecimal taxaFrete;

    @NotNull
    private BigDecimal valorTotal;

    @NotNull
    private Date dataCriacao;

    private StatusPedido status;

    @NotNull
    private Endereco endereco;

    private Date dataDeConfirmacao;

    private Date dataDeCancelamento;

    private Date dataDeEntrega;
}
