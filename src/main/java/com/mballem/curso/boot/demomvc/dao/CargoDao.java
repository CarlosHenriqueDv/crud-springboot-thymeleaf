package com.mballem.curso.boot.demomvc.dao;

import com.mballem.curso.boot.demomvc.domain.Cargo;
import com.mballem.curso.boot.demomvc.util.PaginacaoUtil;

import java.util.List;


public interface CargoDao {

    void save(Cargo departamento);

    void update(Cargo departamento);

    void delete(Long id);

    Cargo findById(Long id);

    List<Cargo> findAll();

    PaginacaoUtil<Cargo> buscaPaginada(int pagina, String direcao);
}
