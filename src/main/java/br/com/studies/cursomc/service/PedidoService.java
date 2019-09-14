package br.com.studies.cursomc.service;

import br.com.studies.cursomc.domanin.Pedido;
import br.com.studies.cursomc.repository.PedidoRepository;
import br.com.studies.cursomc.resources.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    private final PedidoRepository categoriaRepository;

    public PedidoService(PedidoRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Pedido buscar(Integer id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()
        ));
    }
}
