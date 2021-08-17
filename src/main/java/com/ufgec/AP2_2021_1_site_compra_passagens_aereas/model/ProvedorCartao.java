package com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProvedorCartao {
    private static final List<CartaoCompra> cartoesValidos;
    static {
        cartoesValidos = new ArrayList<>();
    }

    public boolean checarValidadeCartao(int numero) {
        return false;
    }

    public void gerarCartao() {

    }

    public class CartaoCompra {
        private double saldo;
        private final int numero;
        private final LocalDate expiracao;

        public CartaoCompra() {
            saldo = 0;
            numero = 0;
            expiracao = null;
        }

        public boolean possuiSaldo(double valor) {
            return false;
        }

        public boolean descontarValor(double valor) {
            return false;
        }
    }
}
