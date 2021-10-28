package com.ufgec.AP2_2021_1_site_compra_passagens_aereas;

import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model.Passageiro;
import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model.Voo;
import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.repository.*;
import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.service.CrudVooService;
import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.utils.Gerador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class SitePassagensApplication implements CommandLineRunner {
    private final VooRepository vooRepository;
    private final AeroportoRepository aeroportoRepository;
    private final PassageiroRepository passageiroRepository;
    @Autowired
    private CrudVooService crudVooService;
    @Autowired
    private Gerador gerador;

    public SitePassagensApplication(VooRepository vooRepository, AeroportoRepository aeroportoRepository, PassageiroRepository passageiroRepository) {
        this.vooRepository = vooRepository;
        this.aeroportoRepository = aeroportoRepository;
        this.passageiroRepository = passageiroRepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(SitePassagensApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        gerador.gerarAeroportosDeJson("src/main/resources/aeroportos.json");
        gerador.gerarVoos(3);

        Passageiro passageiro1 = new Passageiro("Carlos", "Massa",
                LocalDate.of(1950, 12, 24));
        passageiroRepository.save(passageiro1);

        double d = aeroportoRepository.findById("GYN").get().distancia(aeroportoRepository.findById("BSB").get());
        System.out.println(d);

    }
}
