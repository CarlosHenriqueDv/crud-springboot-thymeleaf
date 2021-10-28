package com.boot.crudmvc.service;

import com.boot.crudmvc.dao.DepartamentoDao;
import com.boot.crudmvc.domain.Departamento;
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


    @Transactional
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

    @Override
    public boolean departamentoTemCargos(Long id) {

        if (buscarPorId(id).getCargos().isEmpty()){
            return false;
        }

        return true;

    }
}
