package br.com.studies.cursomc.service;

import br.com.studies.cursomc.domanin.Categoria;
import br.com.studies.cursomc.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria buscar(Integer id) {
        return categoriaRepository.findById(id).orElse(null);

    }
}
