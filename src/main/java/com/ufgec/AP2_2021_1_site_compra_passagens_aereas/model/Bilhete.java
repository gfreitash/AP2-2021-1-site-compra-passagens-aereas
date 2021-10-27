package com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="TB_BILHETE")
public class Bilhete {
    @Id
    private final long codigo;
    @NotBlank
    @OneToOne
    private final Passageiro passageiro;
    @NotBlank
    @OneToOne
    private final Voo voo;
    @NotBlank
    private final int poltrona;


    public Bilhete(Passageiro passageiro, Voo voo, int poltrona) {
        this.passageiro = passageiro;
        this.voo = voo;
        this.poltrona = poltrona;
        codigo = 0;
    }
}
