package br.com.studies.cursomc.service;

import br.com.studies.cursomc.domanin.Cliente;
import br.com.studies.cursomc.repository.ClienteRepository;
import br.com.studies.cursomc.resources.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository categoriaRepository;

    public ClienteService(ClienteRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Cliente buscar(Integer id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()
        ));
    }
}
