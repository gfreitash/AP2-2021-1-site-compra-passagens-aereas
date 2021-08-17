package com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model;

import java.time.LocalDate;

public class Passageiro {
    private final String nome;
    private final String sobrenome;
    private final LocalDate dataNascimento;

    public Passageiro(String nome, String sobrenome, LocalDate dataNascimento) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }
    public String getSobrenome() {
        return sobrenome;
    }
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getNomeCompleto() {
        return nome + sobrenome;
    }

    public boolean isMenorIdade() {
        return false;
    }

}
