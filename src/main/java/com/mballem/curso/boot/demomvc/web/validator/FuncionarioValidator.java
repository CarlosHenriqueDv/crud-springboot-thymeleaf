package com.mballem.curso.boot.demomvc.web.validator;

import com.mballem.curso.boot.demomvc.domain.Funcionario;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.util.Objects;

public class FuncionarioValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Funcionario.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        Funcionario funcionario = (Funcionario) object;

        LocalDate entrada = funcionario.getDataEntrada();

        if (Objects.nonNull(funcionario.getDataSaida())){
            if (funcionario.getDataSaida().isBefore(entrada)){
                errors.rejectValue("dataSaida", "PosteriorDataEntrada.funcionario.dataSaida");
            }
        }
    }
}
