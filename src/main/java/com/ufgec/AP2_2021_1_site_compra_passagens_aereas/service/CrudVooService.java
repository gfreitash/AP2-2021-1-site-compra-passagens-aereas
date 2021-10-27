package com.ufgec.AP2_2021_1_site_compra_passagens_aereas.service;

import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model.Passageiro;
import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model.Voo;
import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model.VooPassageiro;
import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model.VooPassageiroId;
import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.repository.AeroportoRepository;
import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.repository.VooPassageiroRepository;
import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.repository.VooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

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
        aeroportoRepository.save(voo.getOrigem());
        aeroportoRepository.save(voo.getDestino());
    }

    public void reservarPoltrona(Voo voo, Passageiro passageiro, int poltrona) {
        VooPassageiro vooPassageiro = new VooPassageiro(voo, passageiro, poltrona);
        vooPassageiroRepository.save(vooPassageiro);
    }
}
