package com.ufgec.AP2_2021_1_site_compra_passagens_aereas.repository;

import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model.VooPassageiro;
import com.ufgec.AP2_2021_1_site_compra_passagens_aereas.model.VooPassageiroId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VooPassageiroRepository extends CrudRepository<VooPassageiro, VooPassageiroId> {
}
