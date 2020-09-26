package com.mballem.curso.boot.demomvc.dao;

import com.mballem.curso.boot.demomvc.domain.Funcionario;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public class FuncionarioDaoImpl extends AbstractDao<Funcionario, Long> implements FuncionarioDao {


    @Override
    @Transactional
    public List<Funcionario> findByNome(String nome) {
        return createQuery("SELECT f FROM Funcionario f WHERE f.nome LIKE CONCAT('%', ?1, '%') ", nome);
    }

    @Override
    public List<Funcionario> findByCargoId(Long id) {
        return createQuery("select f from Funcionario f where f.cargo.id = ?1", id);
    }

    @Override
    public List<Funcionario> findByDataEntradaDataSaida(LocalDate entrada, LocalDate saida) {
        String jpql = new StringBuilder("SELECT f FROM Funcionario f ")
                .append("where f.dataEntrada >= ?1 and f.dataSaida <= ?2")
                .append("order by f.dataEntrada asc").toString();

        return createQuery(jpql, entrada, saida);
    }

    @Override
    public List<Funcionario> findByDataEntrada(LocalDate entrada) {
        String jpql = new StringBuilder("SELECT f FROM Funcionario f ")
                .append("where f.dataEntrada = ?1 ")
                .append("order by f.dataEntrada asc").toString();

        return createQuery(jpql, entrada);

    }

    @Override
    public List<Funcionario> findByDataSaida(LocalDate saida) {
        String jpql = new StringBuilder("SELECT f FROM Funcionario f ")
                .append("where f.dataSaida = ?1")
                .append("order by f.dataEntrada asc").toString();

        return createQuery(jpql, saida);
    }
}
