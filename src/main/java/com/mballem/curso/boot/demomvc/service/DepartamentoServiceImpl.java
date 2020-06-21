package com.mballem.curso.boot.demomvc.service;

import com.mballem.curso.boot.demomvc.dao.DepartamentoDao;
import com.mballem.curso.boot.demomvc.domain.Cargo;
import com.mballem.curso.boot.demomvc.domain.Departamento;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    DepartamentoDao dao;

    @Transactional
    @Override
    public void salvar(Departamento departamento) {
        dao.save(departamento);
    }
    @Transactional
    @Override
    public void editar(Departamento departamento) {
dao.update(departamento);
    }
    @Transactional(readOnly = true)
    @Override
    public void excluir(Long id) {
dao.delete(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Departamento buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Departamento> buscarTodos() {
        return dao.findAll();
    }
}
