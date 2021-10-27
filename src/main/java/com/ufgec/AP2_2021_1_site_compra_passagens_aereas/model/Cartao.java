package com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model;

import org.apache.tomcat.jni.Local;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Cartao {
    private double saldo;
    @Id
    private final int numero;
    private final LocalDate expiracao;

    public Cartao(int numero, double saldo, LocalDate expiracao) {
        this.numero = numero;
        this.saldo = saldo;
        this.expiracao = expiracao;
    }

    public int getNumero() {
        return numero;
    }

    public boolean estaExpirado() {
        return LocalDate.now().isAfter(expiracao);
    }

    public boolean possuiSaldo(double valor) {
        return false;
    }

    public boolean descontarValor(double valor) {
        return false;
    }
}
