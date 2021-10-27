package com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Voo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    }

    public Voo(Aeroporto origem, Aeroporto destino, LocalDateTime horarioPartida) {
        this.origem = origem;
        this.destino = destino;
        this.horarioPartida = horarioPartida;
        previsaoChegada = calcularPrevisaoChegada();
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
