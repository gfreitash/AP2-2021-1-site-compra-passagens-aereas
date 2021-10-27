package com.ufgec.AP2_2021_1_site_compra_passagens_aereas;

import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model.Aeroporto;
import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model.Voo;
import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.repository.AeroportoRepository;
import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.repository.VooRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class SitePassagensApplication implements CommandLineRunner {
    private final VooRepository vooRepository;
    private final AeroportoRepository aeroportoRepository;

    public SitePassagensApplication(VooRepository vooRepository, AeroportoRepository aeroportoRepository) {
        this.vooRepository = vooRepository;
        this.aeroportoRepository = aeroportoRepository;
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
        aeroportoRepository.save(goiania);
        aeroportoRepository.save(brasilia);

    }
}
