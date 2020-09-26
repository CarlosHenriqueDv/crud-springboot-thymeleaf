package com.mballem.curso.boot.demomvc.web.controller;

import com.mballem.curso.boot.demomvc.domain.Cargo;
import com.mballem.curso.boot.demomvc.domain.Funcionario;
import com.mballem.curso.boot.demomvc.domain.UF;
import com.mballem.curso.boot.demomvc.service.CargoService;
import com.mballem.curso.boot.demomvc.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private CargoService cargoService;


    @GetMapping("/cadastrar")
    public String cadastrar(Funcionario funcionario){
        return "/funcionario/cadastro";
    }

    @GetMapping("/listar")
    public String listar(Model model){
        model.addAttribute("funcionarios", funcionarioService.buscarTodos());
        return "/funcionario/lista";

    }

    @PostMapping("/salvar")
    public String salvar(Funcionario funcionario, RedirectAttributes attr) {
        funcionarioService.salvar(funcionario);
        attr.addFlashAttribute("success", "Funcionário inserido com sucesso.");
        return "redirect:/funcionarios/cadastrar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap modelMap){

        modelMap.addAttribute("funcionario", funcionarioService.buscarPorId(id));
        return "funcionario/cadastro";

    }

    @PostMapping("editar")
    public String editar(Funcionario funcionario, RedirectAttributes attr){
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
        return "/funcionario/lista";

    }

    @GetMapping("/buscar/cargo")
    public String getPorCargo(@RequestParam("id") Long id, ModelMap modelMap){
        modelMap.addAttribute("funcionarios", funcionarioService.buscaPorCargo(id));
        return "/funcionario/lista";
    }

    @GetMapping("/buscar/data")
    public String getPorData(@RequestParam("entrada") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate entrada,
                             @RequestParam("saida") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate saida,
                             ModelMap modelMap){
        modelMap.addAttribute("funcionarios", funcionarioService.buscarPorDatas(entrada, saida));

        return "/funcionario/lista";
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
