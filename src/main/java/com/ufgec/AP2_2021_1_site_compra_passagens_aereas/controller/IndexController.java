package com.ufgec.AP2_2021_1_site_compra_passagens_aereas.controller;

import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model.Aeroporto;
import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.repository.AeroportoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    AeroportoRepository aeroportoRepository;

    @GetMapping("/")
    public String index(Model model){
        List<String> aeroportos = new ArrayList<>();
        aeroportoRepository.findAll().forEach(a -> {
            aeroportos.add(a.getNome() + ", " + a.getCidade() + " - " + a.getSiglaEstado());
        });

        model.addAttribute("aeroportos", aeroportos);

        return "index";
    }
}
