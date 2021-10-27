package com.ufgec.AP2_2021_1_site_compra_passagens_aereas.repository;

import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model.Compra;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends CrudRepository<Compra, Long> {
}
