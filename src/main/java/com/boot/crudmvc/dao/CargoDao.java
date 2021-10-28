package com.boot.crudmvc.dao;

import com.boot.crudmvc.util.PaginacaoUtil;
import com.boot.crudmvc.domain.Cargo;

import java.util.List;


public interface CargoDao {

    void save(Cargo departamento);

    void update(Cargo departamento);

    void delete(Long id);

    Cargo findById(Long id);

    List<Cargo> findAll();

    PaginacaoUtil<Cargo> buscaPaginada(int pagina, String direcao);
}
