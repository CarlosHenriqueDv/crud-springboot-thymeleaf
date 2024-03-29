package com.boot.crudmvc.service;

import com.boot.crudmvc.dao.CargoDao;
import com.boot.crudmvc.util.PaginacaoUtil;
import com.boot.crudmvc.domain.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional // readOnly tem valor padrão false, se colocar como true serve para não abrir conexão com banco quando só for fazer leitura
public class CargoServiceImpl implements CargoService {

    @Autowired
    private CargoDao dao;


    @Override
    @Transactional
    public void salvar(Cargo cargo) {
        dao.save(cargo);
    }

    @Override
    @Transactional
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

    @Override
    public boolean cargoTemFuncionarios(Long id) {
        if (buscarPorId(id).getFuncionarios().isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public PaginacaoUtil<Cargo> buscaPorPaginada(int pagina, String direcao) {
        return dao.buscaPaginada(pagina, direcao);
    }
}
