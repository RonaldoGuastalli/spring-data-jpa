package br.com.studies.cursomc.resources;

import br.com.studies.cursomc.domanin.Cliente;
import br.com.studies.cursomc.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteResources {

    private final ClienteService categoriaService;

    public ClienteResources(ClienteService categoriaService) {
        this.categoriaService = categoriaService;
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> find(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(categoriaService.buscar(id));
    }

}
