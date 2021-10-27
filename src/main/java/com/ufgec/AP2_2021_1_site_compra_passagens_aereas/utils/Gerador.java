package com.ufgec.AP2_2021_1_site_compra_passagens_aereas.utils;

import com.google.gson.Gson;
import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model.Aeroporto;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Gerador {
    public static void gerarAeroportosDeJson(String localArquivo) {
        StringBuilder aeroportoJson = new StringBuilder();
        File file = new File(localArquivo);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                aeroportoJson.append(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Aeroporto[] aeroportosArquivo = new Gson().fromJson(String.valueOf(aeroportoJson), Aeroporto[].class);

        List<Aeroporto> aeroportos = new ArrayList<>();
        Collections.addAll(aeroportos, aeroportosArquivo);

        for(Aeroporto aeroporto: aeroportos) {

        }
    }

    public static void gerarVoos() {

    }
}
