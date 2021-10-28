package com.boot.crudmvc.web.controller;

import com.boot.crudmvc.domain.Cargo;
import com.boot.crudmvc.domain.Departamento;
import com.boot.crudmvc.service.CargoService;
import com.boot.crudmvc.service.DepartamentoService;
import com.boot.crudmvc.util.PaginacaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cargos")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @Autowired
    private DepartamentoService departamentoService;

    private enum RESOURCES{
        CADASTRO_CARGO("cargo/cadastro"), LISTA_CARGO("cargo/lista");

        RESOURCES(String path) {
            this.path = path;
        }

        private String path;

    }

    @GetMapping("/cadastrar")
    public String cadastrar(Cargo cargo) {
        return RESOURCES.CADASTRO_CARGO.path;
    }

    @GetMapping("/listar")
    public String listar(ModelMap model,
                         @RequestParam("page")Optional<Integer> page,
                         @RequestParam("dir")Optional<String> dir) {

        int paginaAtual = page.orElse(1);
        String ordem = dir.orElse("asc");

        PaginacaoUtil<Cargo> pageCargo = cargoService.buscaPorPaginada(paginaAtual, ordem);

        model.addAttribute("pageCargo", pageCargo);
        return RESOURCES.LISTA_CARGO.path;
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()){
            return RESOURCES.CADASTRO_CARGO.path;
        }

        cargoService.salvar(cargo);
        attr.addFlashAttribute("success", "Cargo inserido com sucesso.");
        return "redirect:/cargos/cadastrar";
    }

    @ModelAttribute("departamentos")
    public List<Departamento> listaDeDepartamentos() {
        return departamentoService.buscarTodos();
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr){
        if (cargoService.cargoTemFuncionarios(id)){
            attr.addFlashAttribute("fail", "Cargo não excluido. Tem funcionário(s) vinculado(s).");
        }else{
            cargoService.excluir(id);
            attr.addFlashAttribute("success", "Cargo excluído com sucesso.");
        }
        return "redirect:/cargos/listar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {

        model.addAttribute("cargo", cargoService.buscarPorId(id));
        return RESOURCES.CADASTRO_CARGO.path;
    }

    @PostMapping("/editar")
    public String editar(@Valid Cargo cargo, BindingResult result , RedirectAttributes attr){

        if (result.hasErrors()){
            return RESOURCES.CADASTRO_CARGO.path;
        }

        cargoService.editar(cargo);
        attr.addFlashAttribute("Success", "Cargo editado com sucesso.");
        return "redirect:/cargos/cadastrar";
    }


}
