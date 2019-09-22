package br.com.studies.cursomc.resources;

import br.com.studies.cursomc.domanin.Pedido;
import br.com.studies.cursomc.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoResources {

    private final PedidoService categoriaService;

    public PedidoResources(PedidoService categoriaService) {
        this.categoriaService = categoriaService;
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Pedido> find(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(categoriaService.buscar(id));
    }

}
