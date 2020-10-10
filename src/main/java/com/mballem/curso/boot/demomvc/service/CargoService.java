package com.mballem.curso.boot.demomvc.service;

import com.mballem.curso.boot.demomvc.domain.Cargo;
import com.mballem.curso.boot.demomvc.util.PaginacaoUtil;

import java.util.List;

public interface CargoService {

    void salvar(Cargo cargo);

    void editar(Cargo cargo);

    void excluir(Long id);

    Cargo buscarPorId(Long id);

    List<Cargo> buscarTodos();

    boolean cargoTemFuncionarios(Long id);

    PaginacaoUtil<Cargo> buscaPorPaginada(int pagina, String direcao);
}
