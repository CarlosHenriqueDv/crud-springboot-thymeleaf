package com.mballem.curso.boot.demomvc.web.controller;

import com.mballem.curso.boot.demomvc.domain.Departamento;
import com.mballem.curso.boot.demomvc.service.DepartamentoService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping("/cadastrar")
    public String cadastrar(Departamento departamento){
        return "/departamento/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model){
        model.addAttribute("departamentos", departamentoService.buscarTodos());
        return "/departamento/lista";
    }

    @PostMapping("/salvar")
    public String salvar(Departamento departamento){
        departamentoService.salvar(departamento);
        return "redirect:/departamentos/cadastrar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model){
        model.addAttribute("departamento", departamentoService.buscarPorId(id));
        return "/departamento/cadastro";

    }

    @PostMapping("/editar")
    public String editar(Departamento departamento){
        departamentoService.editar(departamento);
        return "redirect:/departamentos/cadastrar";
    }
}
