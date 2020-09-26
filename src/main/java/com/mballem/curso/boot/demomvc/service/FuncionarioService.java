package com.mballem.curso.boot.demomvc.service;

import com.mballem.curso.boot.demomvc.domain.Funcionario;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


public interface FuncionarioService {

    void salvar(Funcionario funcionario);

    void editar(Funcionario funcionario);

    void excluir(Long id);

    Funcionario buscarPorId(Long id);

    List<Funcionario> buscarTodos();

    List<Funcionario> buscaPorNome(String nome);

    List<Funcionario> buscaPorCargo(Long id);

    List<Funcionario> buscarPorDatas(LocalDate entrada, LocalDate saida);
}
