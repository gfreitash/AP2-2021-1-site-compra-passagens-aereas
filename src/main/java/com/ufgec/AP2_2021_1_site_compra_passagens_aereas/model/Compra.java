package com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model;

import java.util.List;
import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model.ProvedorCartao.CartaoCompra;

public class Compra {
    private List<Bilhete> bilhetes;
    private CartaoCompra cartao;
    private long codigo;

    public Bilhete gerarBilhete() {
        return null;
    }
}