package com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model;

public class Bilhete {
    private final Passageiro passageiro;
    private final Voo voo;
    private final int poltrona;
    private final long codigo;

    public Bilhete(Passageiro passageiro, Voo voo, int poltrona) {
        this.passageiro = passageiro;
        this.voo = voo;
        this.poltrona = poltrona;
        codigo = 0;
    }
}
