package br.com.studies.cursomc.resources;

import br.com.studies.cursomc.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/categoria")
public class CategoriaResources {

    private final CategoriaService categoriaService;

    public CategoriaResources(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscar(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(categoriaService.buscar(id));
    }

}
