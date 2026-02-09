package com.caigods.adega.infrastructure.repository;

import com.caigods.adega.infrastructure.entitys.Bebida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BebidaRepository extends JpaRepository<Bebida, Integer> {


    List<Bebida> findByAnoFabricacao(Integer anoFabricacao);

    List<Bebida> findByNomeContainingIgnoreCase(String nome);

    List<Bebida> findByEmEnvelhecimento(boolean emEnvelhecimento);
}
