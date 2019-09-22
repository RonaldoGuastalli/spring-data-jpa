package br.com.studies.cursomc.resources;

import br.com.studies.cursomc.domanin.Categoria;
import br.com.studies.cursomc.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/categoria")
public class CategoriaResources {

    private final CategoriaService categoriaService;

    public CategoriaResources(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> buscar(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(categoriaService.find(id));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Categoria categoria){
        categoria = categoriaService.insert(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody Categoria categoria, @PathVariable("id") Integer id){
        categoria.setId(id);
        categoria = categoriaService.update(categoria);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
