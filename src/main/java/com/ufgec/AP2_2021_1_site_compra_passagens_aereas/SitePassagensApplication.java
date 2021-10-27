package com.ufgec.AP2_2021_1_site_compra_passagens_aereas;

import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model.Aeroporto;
import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model.Passageiro;
import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model.Voo;
import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.repository.*;
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
        Aeroporto goiania = new Aeroporto("Goiânia", "GYN", "Goiânia", "Goiás"
                ,"GO", 100000, 0.456, -18.356);
        Aeroporto brasilia = new Aeroporto("Brasília", "BSB", "Brasília", "Distrito Federal",
                "DF", 5000000, 122.566, -412.9809);
        aeroportoRepository.save(goiania);
        aeroportoRepository.save(brasilia);

        Voo voo = new Voo(goiania, brasilia, LocalDateTime.now());
        vooRepository.save(voo);

        Passageiro passageiro1 = new Passageiro("Carlos", "Massa",
                LocalDate.of(1950, 12, 24));
        passageiroRepository.save(passageiro1);

        voo.reservarPoltrona(passageiro1, 128);

    }
}
