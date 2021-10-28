package com.ufgec.AP2_2021_1_site_compra_passagens_aereas.repository;

import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model.Aeroporto;
import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model.Voo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VooRepository extends CrudRepository<Voo, Long> {

    @Query("SELECT v " +
            "FROM Voo v " +
            "WHERE v.destino = :aeroporto and v.horarioPartida >= :dataInicio and v.horarioPartida <= :dataMax")
    public List<Voo> encontrarVoosParaAeroportoNumIntervalo(Aeroporto aeroporto,
                                                            LocalDateTime dataInicio, LocalDateTime dataMax);

    @Query("SELECT v " +
            "FROM Voo v " +
            "WHERE v.origem = :aeroporto and v.horarioPartida >= :dataInicio and v.horarioPartida <= :dataMax")
    public List<Voo> encontrarVoosDeAeroportoNumIntervalo(Aeroporto aeroporto,
                                                            LocalDateTime dataInicio, LocalDateTime dataMax);
}
