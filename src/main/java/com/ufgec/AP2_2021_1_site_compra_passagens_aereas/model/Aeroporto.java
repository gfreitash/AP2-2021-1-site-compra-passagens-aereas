package com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model;

import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.utils.Haversine;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Entity
public class Aeroporto implements Comparable<Aeroporto>{
    private final String nome;
    @Id
    private final String sigla;
    private final String cidade;
    private final String estado;
    private final String siglaEstado;

    private int numeroPassageirosAno;

    private final double latitude;
    private final double longitude;

    public Aeroporto() {
        nome = "";
        sigla = "";
        cidade = "";
        estado = "";
        siglaEstado = "";

        latitude = 0.;
        longitude = 0.;
    }

    public Aeroporto(String nome, String sigla, String cidade, String estado, String siglaEstado,
                     int numeroPassageirosAno, double latitude, double longitude) {
        this.nome = nome;
        this.sigla = sigla;
        this.cidade = cidade;
        this.estado = estado;
        this.siglaEstado = siglaEstado;
        this.numeroPassageirosAno = numeroPassageirosAno;

        this.latitude = latitude;
        this.longitude = longitude;

    }

    public String getNome() {
        return nome;
    }
    public String getSigla() {
        return sigla;
    }
    public String getCidade() {
        return cidade;
    }
    public String getEstado() {
        return estado;
    }
    public String getSiglaEstado() {
        return siglaEstado;
    }
    public int getNumeroPassageirosAno() {
        return numeroPassageirosAno;
    }
    public int getNumeroPassageirosAnoRetificado() {
        return retificarNumeroPassageirosAno(numeroPassageirosAno);
    }
    /*public List<Voo> getVoosParaAqui() {
        return voosParaAqui;
    }*/

    /**
     * Este método retifica a quantidade de passageiros anuais de um aeroporto para um valor menor usando uma fórmula
     * arbitrária, tornando a quantidade de voos que serão gerados mais acessível.
     */
    private int retificarNumeroPassageirosAno(int numeroPassageirosAno) {
        if (numeroPassageirosAno <= 350000)
            return numeroPassageirosAno;
        else
            return (numeroPassageirosAno/30) + (int) (50 * Math.sqrt(25 * numeroPassageirosAno));
    }

    /**
     * Este método calcula a distância entre dois aeroportos a partir
     * da fórmula de Haversine
     * @param outroAeroporto é o outro aeroporto que será medida a distância
     * @return distância entre dois aeroportos;
     */
    public double distancia(Aeroporto outroAeroporto) {
        return Haversine.distance(this.latitude, this.longitude, outroAeroporto.latitude, outroAeroporto.longitude);
    }

    public List<Voo> voosDaquiParaNumIntervalo(Aeroporto para, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return null;
    }

    public List<Voo> voosParaAquiDeNumIntervalo(Aeroporto de, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Aeroporto aeroporto)) return false;
        return sigla.equals(aeroporto.sigla);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sigla);
    }

    @Override
    public int compareTo(Aeroporto o) {
        return Comparator.comparing(Aeroporto::getNumeroPassageirosAno)
                .thenComparing(Aeroporto::getSigla)
                .thenComparing(Aeroporto::getEstado)
                .thenComparing(Aeroporto::getCidade)
                .compare(this, o);
    }
}
