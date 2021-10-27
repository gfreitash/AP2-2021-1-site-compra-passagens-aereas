package com.ufgec.AP2_2021_1_site_compra_passagens_aereas.service;

import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model.Aeroporto;
import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.repository.AeroportoRepository;
import org.springframework.stereotype.Service;

@Service
public class CrudAeroportoService {
    private final AeroportoRepository aeroportoRepository;

    public CrudAeroportoService(AeroportoRepository aeroportoRepository) {
        this.aeroportoRepository = aeroportoRepository;
    }

    public void salvar(Aeroporto aeroporto) {
        aeroportoRepository.save(aeroporto);
    }
}
