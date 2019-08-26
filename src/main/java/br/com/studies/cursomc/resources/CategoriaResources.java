package br.com.studies.cursomc.resources;

import br.com.studies.cursomc.domanin.Categoria;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResources {

    @GetMapping
    public List<Categoria> listar(){
        Categoria categoria1 = new Categoria(1, "Informática");
        Categoria categoria2 = new Categoria(2, "Ëscritório");

        List<Categoria> categorias = new ArrayList<>();
        categorias.add(categoria1);
        categorias.add(categoria2);

        return categorias;
    }

}
