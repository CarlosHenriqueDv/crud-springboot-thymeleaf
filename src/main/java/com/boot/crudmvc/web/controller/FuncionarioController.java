package com.boot.crudmvc.web.controller;

import com.boot.crudmvc.domain.Cargo;
import com.boot.crudmvc.domain.Funcionario;
import com.boot.crudmvc.domain.UF;
import com.boot.crudmvc.service.CargoService;
import com.boot.crudmvc.service.FuncionarioService;
import com.boot.crudmvc.web.validator.FuncionarioValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private CargoService cargoService;

    private enum RESOURCES{
        CADASTRO_FUNCIONARIO("funcionario/cadastro"), LISTAR_FUNCIONARIO("funcionario/lista");

        RESOURCES(String path){
            this.path = path;
        }
        private String path;
    }


    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.addValidators(new FuncionarioValidator());

    }


    @GetMapping("/cadastrar")
    public String cadastrar(Funcionario funcionario){
        return RESOURCES.CADASTRO_FUNCIONARIO.path;
    }

    @GetMapping("/listar")
    public String listar(Model model){
        model.addAttribute("funcionarios", funcionarioService.buscarTodos());
        return RESOURCES.LISTAR_FUNCIONARIO.path;

    }

    @PostMapping("/salvar")
    public String salvar(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()){
            return RESOURCES.CADASTRO_FUNCIONARIO.path;
        }

        funcionarioService.salvar(funcionario);
        attr.addFlashAttribute("success", "Funcionário inserido com sucesso.");
        return "redirect:/funcionarios/cadastrar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap modelMap){

        modelMap.addAttribute("funcionario", funcionarioService.buscarPorId(id));
        return RESOURCES.CADASTRO_FUNCIONARIO.path;

    }

    @PostMapping("editar")
    public String editar(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attr){

        if (result.hasErrors()){
            return RESOURCES.CADASTRO_FUNCIONARIO.path;
        }

        funcionarioService.editar(funcionario);
        attr.addFlashAttribute("success", "Funcionario cadastrado com sucesso");
        return "redirect:/funcionarios/cadastrar";

    }

    @GetMapping("/excluir/{id}")
    public String exluir(@PathVariable("id") Long id, RedirectAttributes attr){

        funcionarioService.excluir(id);
        attr.addFlashAttribute("success", "Funcionario excluido com sucesso" );
        return "redirect:/funcionarios/listar";

    }

    @GetMapping("/buscar/nome")
    public String getPorNome(@RequestParam("nome") String nome, ModelMap modelMap){
        modelMap.addAttribute("funcionarios", funcionarioService.buscaPorNome(nome));
        return RESOURCES.LISTAR_FUNCIONARIO.path;

    }

    @GetMapping("/buscar/cargo")
    public String getPorCargo(@RequestParam("id") Long id, ModelMap modelMap){
        modelMap.addAttribute("funcionarios", funcionarioService.buscaPorCargo(id));
        return RESOURCES.LISTAR_FUNCIONARIO.path;
    }

    @GetMapping("/buscar/data")
    public String getPorData(@RequestParam("entrada") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate entrada,
                             @RequestParam("saida") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate saida,
                             ModelMap modelMap){
        modelMap.addAttribute("funcionarios", funcionarioService.buscarPorDatas(entrada, saida));

        return RESOURCES.LISTAR_FUNCIONARIO.path;
    }



    @ModelAttribute("cargos")
    public List<Cargo> getCargos(){
        return cargoService.buscarTodos();
    }

    @ModelAttribute("ufs")
    public UF[] getUfs(){
        return UF.values();
    }
}
