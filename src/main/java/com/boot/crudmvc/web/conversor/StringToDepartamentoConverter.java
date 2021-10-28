package com.boot.crudmvc.web.conversor;

import com.boot.crudmvc.domain.Departamento;
import com.boot.crudmvc.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToDepartamentoConverter implements Converter<String, Departamento> {

    @Autowired
    private DepartamentoService departamentoService;


    @Override
    public Departamento convert(String text) {
        if (text.isEmpty()){
            return null;
        }

        Long id = Long.valueOf(text);
        return departamentoService.buscarPorId(id);
    }
}
