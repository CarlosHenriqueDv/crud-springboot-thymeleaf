package com.mballem.curso.boot.demomvc.service;

import com.mballem.curso.boot.demomvc.dao.DepartamentoDao;
import com.mballem.curso.boot.demomvc.domain.Cargo;
import com.mballem.curso.boot.demomvc.domain.Departamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    @Autowired
    private DepartamentoDao departamentoDao;

    @Transactional
    @Override
    public void salvar(Departamento departamento) {
        departamentoDao.save(departamento);
    }

    @Transactional
    @Override
    public void editar(Departamento departamento) {
        departamentoDao.update(departamento);
    }


    @Transactional(readOnly = true)
    @Override
    public void excluir(Long id) {
        departamentoDao.delete(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Departamento buscarPorId(Long id) {
        return departamentoDao.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Departamento> buscarTodos() {
        return departamentoDao.findAll();
    }
}
