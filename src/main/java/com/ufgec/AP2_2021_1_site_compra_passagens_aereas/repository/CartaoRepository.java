package com.ufgec.AP2_2021_1_site_compra_passagens_aereas.repository;

import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model.Cartao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends CrudRepository<Cartao, Integer> {
}
