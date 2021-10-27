package com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class VooPassageiro {
    @EmbeddedId
    private VooPassageiroId id;
    private int poltrona;

    public VooPassageiro() {
    }

    public VooPassageiro(Voo voo, Passageiro passageiro, int poltrona) {
        id = new VooPassageiroId(voo, passageiro);
        this.poltrona = poltrona;
    }

    public Voo getVoo() {
        return id.getVoo();
    }

    public void setVoo(Voo voo) {
        id.setVoo(voo);
    }

    public Passageiro getPassageiro() {
        return id.getPassageiro();
    }

    public void setPassageiro(Passageiro passageiro) {
        id.setPassageiro(passageiro);
    }

    public int getPoltrona() {
        return poltrona;
    }

    public void setPoltrona(int poltrona) {
        this.poltrona = poltrona;
    }
}
