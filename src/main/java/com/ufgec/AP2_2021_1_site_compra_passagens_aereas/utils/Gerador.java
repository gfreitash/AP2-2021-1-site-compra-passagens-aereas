package com.ufgec.AP2_2021_1_site_compra_passagens_aereas.utils;

import com.google.gson.Gson;
import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model.Aeroporto;
import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model.Voo;
import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.service.CrudAeroportoService;
import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.service.CrudVooService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Component
public class Gerador {
    private static final Logger logger = LogManager.getLogger(Gerador.class);

    private final CrudAeroportoService crudAeroportoService;
    private final CrudVooService crudVooService;

    //Este bloco define constantes de períodos do dia.
    private static final LocalTime[] MANHA_EARLY = {LocalTime.of(6, 0), LocalTime.of(7, 59)};
    private static final LocalTime[] MANHA_MID = {LocalTime.of(8, 0), LocalTime.of(9, 59)};
    private static final LocalTime[] MANHA_LATE = {LocalTime.of(10, 0), LocalTime.of(11, 59)};
    private static final LocalTime[] TARDE_EARLY = {LocalTime.of(12, 0), LocalTime.of(13, 59)};
    private static final LocalTime[] TARDE_MID = {LocalTime.of(14, 0), LocalTime.of(15, 59)};
    private static final LocalTime[] TARDE_LATE = {LocalTime.of(16, 0), LocalTime.of(17, 59)};
    private static final LocalTime[] NOITE_EARLY = {LocalTime.of(18, 0), LocalTime.of(19, 59)};
    private static final LocalTime[] NOITE_MID = {LocalTime.of(20, 0), LocalTime.of(21, 59)};
    private static final LocalTime[] NOITE_LATE = {LocalTime.of(22, 0), LocalTime.of(23, 59)};
    private static final LocalTime[] MADRUGADA = {LocalTime.of(0, 0), LocalTime.of(5, 59)};
    //--------------------------------------------------------------------------------------------------------------//

    public Gerador(CrudAeroportoService crudAeroportoService, CrudVooService crudVooService) {
        this.crudAeroportoService = crudAeroportoService;
        this.crudVooService = crudVooService;
    }

    public static LocalDateTime gerarHorarioValido(LocalDate data, LocalTime[] intervaloPossivel) {
        int horarioMaximo, horarioMinimo, intervaloPossivelMinutos, minutos;

        if(intervaloPossivel[1].isBefore(intervaloPossivel[0]) || intervaloPossivel[1].equals(intervaloPossivel[0])) {
            int i = (24*60) - (intervaloPossivel[0].getHour() * 60) + intervaloPossivel[0].getMinute();
            int j = (intervaloPossivel[1].getHour() * 60) + intervaloPossivel[1].getMinute();

            intervaloPossivelMinutos = i+j;
            minutos = (int) (Math.random() * intervaloPossivelMinutos);
            minutos = Uteis.arredondarParaProximo5(minutos);

            data = data.plusDays(1);
        } else {
            horarioMaximo = (intervaloPossivel[1].getHour() * 60) + intervaloPossivel[1].getMinute();
            horarioMinimo = (intervaloPossivel[0].getHour() * 60) + intervaloPossivel[0].getMinute();

            intervaloPossivelMinutos = Math.abs(horarioMaximo - horarioMinimo);
            minutos = (int) (Math.random() * intervaloPossivelMinutos);
            minutos = Uteis.arredondarParaProximo5(minutos);
        }

        LocalTime horarioValido = intervaloPossivel[0].plusMinutes(minutos);
        return LocalDateTime.of(data, horarioValido);
    }

    public void gerarVoos(int dias) {
        //----------------------------------------------------------------------------------------------------------//
        /*Este bloco de código insere as constantes de períodos do dia em uma lista
         * que os retorna de forma aleatória levando em consideração o valor do peso atribuído.
         */
        ListaAleatoriaPonderada<LocalTime[]> probabilidadeHorario = new ListaAleatoriaPonderada<>();
        probabilidadeHorario.adicionar(MANHA_EARLY, 1);
        probabilidadeHorario.adicionar(MANHA_MID, 2.5); //(1.0, 3.5]
        probabilidadeHorario.adicionar(MANHA_LATE, 2); // (3.5, 5.5]
        probabilidadeHorario.adicionar(TARDE_EARLY, 1.5);//(5.5, 7.0]
        probabilidadeHorario.adicionar(TARDE_MID, 1.5);//(7.0, 8.5]
        probabilidadeHorario.adicionar(TARDE_LATE, 1);//(8.5, 9.5]
        probabilidadeHorario.adicionar(NOITE_EARLY, 1);//(9.5, 10.5]
        probabilidadeHorario.adicionar(NOITE_MID, 0.5);//(10.5, 11.0]
        probabilidadeHorario.adicionar(NOITE_LATE, 0.25);//(11.0, 11.25]
        probabilidadeHorario.adicionar(MADRUGADA, 0.5);//(11.25, 11.75]

        //--------------------------------------------------------------------------------------------------------//
        /* Aqui acontece as declarações e inicializações que serão utilizadas
         * durante o loop de criação de voos
         */
        List<Aeroporto> aeroportos = crudAeroportoService.getTodos(); //Lista de todos os aeroportos
        ListaAleatoriaPonderada<Aeroporto> escolhaAeroporto = new ListaAleatoriaPonderada<>();
        for(Aeroporto aeroporto: aeroportos) //Lista de onde os destinos serão escolhidos
            escolhaAeroporto.adicionar(aeroporto, mediaVoosDiarios(aeroporto.getNumeroPassageirosAnoRetificado()));


        int mediaPassageirosAno;
        Aeroporto destino;
        boolean destinoValido;

        LocalDateTime now = LocalDateTime.now(); //Os voos serão criados a partir da data atual
        LocalDate dataVoo;
        LocalTime horaVoo;

        //-------------------------------------------------------------------------------------//
        //visto que a lista escolhaAeroporto dará preferência para aeroportos com muitos voos diarios
        aeroportos.sort(null); //ordena da menor quantidade de passageiros diarios para a maior
        for (Aeroporto origem: aeroportos) { //Cada elemento desse loop é um aeroporto
            for (int i = 0; i < dias; i++) { //Este loop representa cada dia
                //Este loop representa cada voo que sai do aeroporto
                mediaPassageirosAno = mediaVoosDiarios(origem.getNumeroPassageirosAnoRetificado());
                for (int j = 0; j < mediaPassageirosAno/2; j++) {
                    //--------------------------------------------------------------------------------------------//
                    /* Este loop gera um destino válido. Um aeroporto que possui uma movimentação de +130000
                     * passageiros por ano pode gerar um voo para qualquer aeroporto que esteja a
                     * pelo menos 100km de distância.
                     * Caso o aeroporto possua uma movimentação de passageiros inferior a esta, a distância máxima
                     * que um voo poderá ser criado é de 800km.
                     */
                    destinoValido = false;
                    do {
                        destino = escolhaAeroporto.obter();

                        if (!destino.equals(origem) && origem.distancia(destino) >= 100)
                            if (origem.getNumeroPassageirosAnoRetificado() >= 130000)
                                destinoValido = true;
                            else if (origem.distancia(destino) < 800)
                                destinoValido = true;

                    } while (!destinoValido);

                    //--------------------------------------------------------------------------------------------//

                    dataVoo = now.toLocalDate().plusDays(i);
                    LocalTime[] tempo = probabilidadeHorario.obter();
                    LocalDateTime horarioVooIda = gerarHorarioValido(dataVoo, tempo);

                    Voo ida = new Voo(origem, destino, horarioVooIda);
                    crudVooService.salvar(ida);

                    //--------------------------------------------------------------------------------------------//
                    //Agendamento de voo de retorno
                    LocalTime[] intervalo = new LocalTime[2];
                    LocalDateTime horarioVooRetorno;

                    intervalo[0] = ida.getPrevisaoChegada().plusMinutes(45).toLocalTime();
                    intervalo[1] = ida.getHorarioPartida().toLocalTime();

                    dataVoo = now.toLocalDate().plusDays(i);
                    horarioVooRetorno = gerarHorarioValido(dataVoo, intervalo);

                    Voo retorno = new Voo(destino, origem, horarioVooRetorno);
                    crudVooService.salvar(retorno);
                }
            }
        }
    }

    public void gerarAeroportosDeJson(String localArquivo) {
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
            crudAeroportoService.salvar(aeroporto);
        }
    }

    /**
     * Cacula a média de voos diários baseado na quantidade de passageiros ao ano
     * e 70% da lotação dos 60 assentos.
     * @param quantidadePassageirosAno
     * @return
     */
    private int mediaVoosDiarios(int quantidadePassageirosAno) {
        int i = (int) ((quantidadePassageirosAno/365) / (60*0.7));
        return i;
    }
}
