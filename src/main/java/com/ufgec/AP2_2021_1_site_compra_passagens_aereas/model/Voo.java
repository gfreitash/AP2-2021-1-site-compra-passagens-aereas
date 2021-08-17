package com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Voo {
    private final Aeroporto localOrigem;
    private final Aeroporto localDestino;
    private final LocalDateTime horarioPartida;
    private final LocalDateTime previsaoChegada;

    private final Map<Integer, Passageiro> poltronas;
    private long id;

    Voo(Aeroporto localOrigem, Aeroporto localDestino, LocalDateTime horarioPartida) {
        this.localOrigem = localOrigem;
        this.localDestino = localDestino;
        this.horarioPartida = horarioPartida;
        previsaoChegada = null;

        poltronas = new HashMap<>();
        id = 0;
    }

    public Aeroporto getLocalOrigem() {
        return localOrigem;
    }
    public Aeroporto getLocalDestino() {
        return localDestino;
    }
    public LocalDateTime getHorarioPartida() {
        return horarioPartida;
    }
    public LocalDateTime getPrevisaoChegada() {
        return previsaoChegada;
    }
    public Map<Integer, Passageiro> getPoltronas() {
        return poltronas;
    }
    public long getId() {
        return id;
    }

    public boolean reservarPoltrona(int numeroPoltrona, Bilhete bilhete) {
        return false;
    }

    public boolean poltronaEstaDisponivel(int poltrona) {
        return false;
    }

    public Passageiro getPassageiroPoltrona(int poltrona) {
        return null;
    }
}
