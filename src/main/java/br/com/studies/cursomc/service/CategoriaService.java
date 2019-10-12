package br.com.studies.cursomc.service;

import br.com.studies.cursomc.domanin.Categoria;
import br.com.studies.cursomc.dto.CategoriaDTO;
import br.com.studies.cursomc.repository.CategoriaRepository;
import br.com.studies.cursomc.resources.exception.DataIntegrityException;
import br.com.studies.cursomc.resources.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria find(Integer id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()
        ));
    }

    @Transactional
    public Categoria insert(Categoria categoria) {
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

    public Categoria update(Categoria categoria) {
        Categoria newCategoria = find(categoria.getId());
        updateData(newCategoria, categoria);
        return categoriaRepository.save(newCategoria);
    }

    private void updateData(Categoria newCategoria, Categoria categoria) {
        newCategoria.setNome(categoria.getNome());
    }

    public void delete(Integer id) {
        find(id);
        try{
            categoriaRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
        }
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return categoriaRepository.findAll(pageRequest);
    }

    //TODO: alterar para um mapper
    public Categoria fromDTO(CategoriaDTO categoriaDTO) {
        return Categoria.builder()
                .id(categoriaDTO.getId())
                .nome(categoriaDTO.getNome())
                .build();
    }
}
