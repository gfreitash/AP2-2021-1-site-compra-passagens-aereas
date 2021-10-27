package com.ufgec.AP2_2021_1_site_compra_passagens_aereas.service;

import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model.Passageiro;
import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.repository.PassageiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrudPassageiroService {
    @Autowired
    PassageiroRepository passageiroRepository;

    public void salvar(Passageiro passageiro) {
        passageiroRepository.save(passageiro);
    }
}
