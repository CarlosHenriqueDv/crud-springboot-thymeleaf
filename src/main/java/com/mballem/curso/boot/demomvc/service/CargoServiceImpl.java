package com.mballem.curso.boot.demomvc.service;

import com.mballem.curso.boot.demomvc.dao.CargoDao;
import com.mballem.curso.boot.demomvc.domain.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional // readOnly tem valor padrão null serve para não abrir conexão com banco quando só for fazer leitura
public class CargoServiceImpl implements CargoService {

    @Autowired
    private CargoDao dao;


    @Override
    @Transactional(readOnly = true)
    public void salvar(Cargo cargo) {
        dao.save(cargo);
    }

    @Override
    @Transactional(readOnly = true)
    public void editar(Cargo cargo) {
        dao.update(cargo);
    }

    @Override
    public void excluir(Long id) {
        dao.delete(id);
    }

    @Override
    public Cargo buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Cargo> buscarTodos() {
        return dao.findAll();
    }
}