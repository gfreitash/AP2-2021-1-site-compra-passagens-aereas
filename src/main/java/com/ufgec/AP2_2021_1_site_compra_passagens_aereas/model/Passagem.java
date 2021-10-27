package com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Passagem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long codigo;
    @OneToOne
    private VooPassageiro vooPassageiro;


    public Passagem() {
    }

    public Passagem(Passageiro passageiro, Voo voo, int poltrona) {
        vooPassageiro = new VooPassageiro(voo, passageiro, poltrona);
    }

    public long getCodigo() {
        return codigo;
    }

    public Passageiro getPassageiro() {
        return vooPassageiro.getPassageiro();
    }

    public Voo getVoo() {
        return vooPassageiro.getVoo();
    }

    public int getPoltrona() {
        return vooPassageiro.getPoltrona();
    }
}
