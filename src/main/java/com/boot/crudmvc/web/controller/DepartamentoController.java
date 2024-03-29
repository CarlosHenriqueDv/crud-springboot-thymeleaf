package com.boot.crudmvc.web.controller;

import com.boot.crudmvc.domain.Departamento;
import com.boot.crudmvc.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    private enum RESOURCE{
        CADASTRO_DEPARTAMENTO("departamento/cadastro"), LISTAR_DEPARTAMENTO("departamento/lista");

        RESOURCE(String path) {
            this.path = path;
        }

        private String path;
    }

    @GetMapping("/cadastrar")
    public String cadastrar(Departamento departamento){
        return RESOURCE.CADASTRO_DEPARTAMENTO.path;
    }

    @GetMapping("/listar")
    public String listar(ModelMap model){
        model.addAttribute("departamentos", departamentoService.buscarTodos());
        return RESOURCE.LISTAR_DEPARTAMENTO.path;
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Departamento departamento, BindingResult result, RedirectAttributes attr){

        if (result.hasErrors()){
            return RESOURCE.CADASTRO_DEPARTAMENTO.path;
        }

        departamentoService.salvar(departamento);
        attr.addFlashAttribute("success", "Departamento inserido com sucesso.");
        return "redirect:/departamentos/cadastrar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model){
        model.addAttribute("departamento", departamentoService.buscarPorId(id));
        return RESOURCE.CADASTRO_DEPARTAMENTO.path;

    }

    @PostMapping("/editar")
    public String editar(@Valid Departamento departamento, BindingResult result, RedirectAttributes attr){

        if (result.hasErrors()){
            return RESOURCE.CADASTRO_DEPARTAMENTO.path;
        }

        departamentoService.editar(departamento);
        attr.addFlashAttribute("success", "Departamento editado com sucesso.");
        return "redirect:/departamentos/cadastrar";
    }

    @GetMapping("/excluir/{id}")
    public String excluirDepartamento(@PathVariable("id") Long id, ModelMap modelMap){

        if (departamentoService.departamentoTemCargos(id)){
            modelMap.addAttribute("fail", "Departamento não removido, Possui cargo(s) vinculado(s).");
        }else{
            departamentoService.excluir(id);
            modelMap.addAttribute("success", "Departamento removido com sucesso.");
        }
        return listar(modelMap);
    }
}
