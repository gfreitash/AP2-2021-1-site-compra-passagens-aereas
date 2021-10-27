package com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.*;

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long codigo;
    @OneToMany
    private final List<Passagem> passagens = new ArrayList<>();
    @ManyToOne
    private Cartao cartao;
    private LocalDate dataCompra;

    public Compra() {
    }

    public Compra(Cartao cartao, Passagem ... passagens) {
        this.cartao = cartao;
        Collections.addAll(this.passagens, passagens);

        dataCompra = LocalDate.now();
    }

    public long getCodigo() {
        return codigo;
    }

    public List<Passagem> getPassagens() {
        return passagens;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
