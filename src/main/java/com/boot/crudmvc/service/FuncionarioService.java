package com.boot.crudmvc.service;

import com.boot.crudmvc.domain.Funcionario;

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
