package com.mballem.curso.boot.demomvc.dao;

import com.mballem.curso.boot.demomvc.domain.Funcionario;

import java.time.LocalDate;
import java.util.List;

public interface FuncionarioDao {

    void save(Funcionario departamento);

    void update(Funcionario departamento);

    void delete(Long id);

    Funcionario findById(Long id);

    List<Funcionario> findAll();

    List<Funcionario> findByNome(String nome);

    List<Funcionario> findByCargoId(Long id);

    List<Funcionario> findByDataEntradaDataSaida(LocalDate entrada, LocalDate saida);

    List<Funcionario> findByDataEntrada(LocalDate entrada);

    List<Funcionario> findByDataSaida(LocalDate saida);
}
