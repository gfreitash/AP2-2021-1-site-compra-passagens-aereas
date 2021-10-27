package com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Voo {
    @Id
    private long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private final LocalDateTime horarioPartida;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private final LocalDateTime previsaoChegada;

    @ManyToOne
    private final Aeroporto origem;
    @ManyToOne
    private final Aeroporto destino;

    //private final Map<Integer, Passageiro> poltronas;

    public Voo(){
        horarioPartida = null;
        previsaoChegada = null;
        origem = null;
        destino = null;
        id = hashCode();
    }

    public Voo(Aeroporto origem, Aeroporto destino, LocalDateTime horarioPartida) {
        this.origem = origem;
        this.destino = destino;
        this.horarioPartida = horarioPartida;
        previsaoChegada = calcularPrevisaoChegada();
        id = hashCode();
    }

    private LocalDateTime calcularPrevisaoChegada() {
        long tempoViagemMinutos = (long) ((origem.distancia(destino)/950) * 60);
        return horarioPartida.plusMinutes(tempoViagemMinutos);
    }

    public LocalDateTime getHorarioPartida() {
        return horarioPartida;
    }
    public LocalDateTime getPrevisaoChegada() {
        return previsaoChegada;
    }
    public Aeroporto getOrigem() {
        return origem;
    }
    public Aeroporto getDestino() {
        return destino;
    }

    public long getId() {
        return id;
    }

    public boolean poltronaEstaDisponivel(int poltrona) {
        return false;
    }

    public Passageiro getPassageiroPoltrona(int poltrona) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Voo)) return false;
        Voo voo = (Voo) o;
        return horarioPartida.equals(voo.horarioPartida) && previsaoChegada.equals(voo.previsaoChegada) && origem.equals(voo.origem) && destino.equals(voo.destino);
    }

    @Override
    public int hashCode() {
        return Objects.hash(horarioPartida, previsaoChegada, origem, destino);
    }
}
