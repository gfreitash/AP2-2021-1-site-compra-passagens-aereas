package com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class Aeroporto {
    private final String nome;
    private final String sigla;
    private final String cidade;
    private final String estado;
    private final String siglaEstado;

    private int numeroPassageirosAno;

    private final double latitude;
    private final double longitude;

    /**
     * Nesta variável estão armazenados todos os aeroportos válidos(criados)
     */
    private static final SortedMap<String, Aeroporto> AEROPORTOS = new TreeMap<>();

    private Aeroporto(String nome, String sigla, String cidade, String estado, String siglaEstado,
                      int numeroPassageirosAno, double latitude, double longitude) {
        this.nome = nome;
        this.sigla = sigla;
        this.cidade = cidade;
        this.estado = estado;
        this.siglaEstado = siglaEstado;
        this.numeroPassageirosAno = retificarNumeroPassageirosAno(numeroPassageirosAno);

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

    /**
     * Este método retifica a quantidade de passageiros anuais de um aeroporto para um valor menor usando uma fórmula
     * arbitrária, tornando a quantidade de voos que serão gerados mais acessível.
     */
    private int retificarNumeroPassageirosAno(int numeroPassageirosAno) {
        if (numeroPassageirosAno <= 37500)
            return numeroPassageirosAno;
        else
            return (int) (50 * Math.sqrt(15 * numeroPassageirosAno));
    }

    public double calcularDistancia() {
        return 0.0;
    }

    public List<Voo> voosDaquiParaNumIntervalo(Aeroporto para, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return null;
    }

    public List<Voo> voosParaAquiDeNumIntervalo(Aeroporto de, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return null;
    }

}
