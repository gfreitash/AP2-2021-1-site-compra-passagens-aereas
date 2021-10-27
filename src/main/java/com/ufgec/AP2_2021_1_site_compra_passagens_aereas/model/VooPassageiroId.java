package com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VooPassageiroId implements Serializable {
    @ManyToOne
    private Voo voo;
    @ManyToOne
    private Passageiro passageiro;

    public VooPassageiroId() {
    }
    public VooPassageiroId(Voo voo, Passageiro passageiro) {
        this.voo = voo;
        this.passageiro = passageiro;
    }

    public Voo getVoo() {
        return voo;
    }

    public void setVoo(Voo voo) {
        this.voo = voo;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VooPassageiroId that)) return false;
        return voo.equals(that.voo) && passageiro.equals(that.passageiro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voo, passageiro);
    }
}
