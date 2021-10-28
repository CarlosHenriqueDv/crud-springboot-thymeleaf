package com.boot.crudmvc.dao;

import com.boot.crudmvc.util.PaginacaoUtil;
import com.boot.crudmvc.domain.Cargo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CargoDaoImpl extends AbstractDao<Cargo, Long> implements CargoDao{

    public PaginacaoUtil<Cargo> buscaPaginada(int pagina, String direcao){

        int tamanho = 5;
        int inicio = (pagina - 1) * tamanho; // 0*5=0 1*5=0 2*5=10
        List<Cargo> cargos = getEntityManager()
                .createQuery("select c from Cargo c order by c.nome " + direcao, Cargo.class)
                .setFirstResult(inicio)
                .setMaxResults(tamanho).getResultList();

        long totalRegistros = count();
        long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho;
        return new PaginacaoUtil<>(tamanho, pagina, totalDePaginas, cargos, direcao);
    }

    public long count(){
        return getEntityManager()
                .createQuery("select count(*) from Cargo", Long.class)
                .getSingleResult();
    }


}
