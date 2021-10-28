package com.boot.crudmvc.service;

import com.boot.crudmvc.util.PaginacaoUtil;
import com.boot.crudmvc.domain.Cargo;

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
