package com.ufgec.AP2_2021_1_site_compra_passagens_aereas.service;

import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model.*;
import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.repository.AeroportoRepository;
import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.repository.VooPassageiroRepository;
import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.repository.VooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CrudVooService {
    @Autowired
    private VooRepository vooRepository;
    @Autowired
    private AeroportoRepository aeroportoRepository;
    @Autowired
    private VooPassageiroRepository vooPassageiroRepository;

    public void salvar(Voo voo) {
        vooRepository.save(voo);
    }

    public void reservarPoltrona(Voo voo, Passageiro passageiro, int poltrona) {
        VooPassageiro vooPassageiro = new VooPassageiro(voo, passageiro, poltrona);
    }

    public List<Voo> getTodos() {
        List<Voo> voo = new ArrayList<>();
        vooRepository.findAll().forEach(voo::add);
        return voo;
    }

    public List<Voo> voosParaAeroportoNumIntervalo(Aeroporto aeroporto, LocalDateTime dataInicio, LocalDateTime dataMaxima) {
        return vooRepository.encontrarVoosParaAeroportoNumIntervalo(aeroporto, dataInicio, dataMaxima);
    }

    public List<Voo> voosDeAeroportoNumIntervalo(Aeroporto aeroporto, LocalDateTime dataInicio, LocalDateTime dataMaxima) {
        return vooRepository.encontrarVoosDeAeroportoNumIntervalo(aeroporto, dataInicio, dataMaxima);
    }
}
