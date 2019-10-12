package br.com.studies.cursomc.service;

import br.com.studies.cursomc.domanin.Cidade;
import br.com.studies.cursomc.domanin.Cliente;
import br.com.studies.cursomc.domanin.Endereco;
import br.com.studies.cursomc.dto.ClienteDTO;
import br.com.studies.cursomc.dto.ClienteNewDTO;
import br.com.studies.cursomc.repository.ClienteRepository;
import br.com.studies.cursomc.repository.EnderecoRepository;
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
import java.util.Objects;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente find(Integer id) {
        return clienteRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()
        ));
    }

    public Cliente update(Cliente cliente) {
        Cliente newCliente = find(cliente.getId());
        updateData(newCliente, cliente);
        return clienteRepository.save(newCliente);
    }

    private void updateData(Cliente newCliente, Cliente cliente) {
        newCliente.setNome(cliente.getNome());
        newCliente.setEmail(cliente.getEmail());
    }

    public void delete(Integer id) {
        find(id);
        try{
            clienteRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir uma cliente que possui entidades relacinadas");
        }
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    //TODO: alterar para um mapper
    public Cliente fromDTO(ClienteDTO clienteDTO) {
        return Cliente.builder()
                .id(clienteDTO.getId())
                .nome(clienteDTO.getNome())
                .email(clienteDTO.getEmail())
                .build();
    }

    //TODO: alterar para um mapper
    public Cliente fromDTO(ClienteNewDTO clienteNewDTO) {
        Cliente cliente = Cliente.builder()
                .id(null)
                .nome(clienteNewDTO.getNome())
                .email(clienteNewDTO.getEmail())
                .cpfOuCnpj(clienteNewDTO.getCpfOuCnpj())
                .tipo(clienteNewDTO.getTipo())
                .build();
        Cidade cidade = Cidade.builder()
                .id(clienteNewDTO.getCidadeId())
                .build();
        Endereco endereco = Endereco.builder()
                .id(null)
                .logradouro(clienteNewDTO.getLogradouro())
                .numero(clienteNewDTO.getNumero())
                .complemento(clienteNewDTO.getComplemento())
                .bairro(clienteNewDTO.getBairro())
                .cep(clienteNewDTO.getCep())
                .cidade(cidade)
                .build();
        cliente.getEnderecos().add(endereco);
        setTelefones(clienteNewDTO, cliente);
        return cliente;
    }

    private void setTelefones(ClienteNewDTO clienteNewDTO, Cliente cliente) {
        Objects.isNull(cliente.getTelefones().add(clienteNewDTO.getTelefone1()));
        if(clienteNewDTO.getTelefone2() != null)
            cliente.getTelefones().add(clienteNewDTO.getTelefone2());
        if(clienteNewDTO.getTelefone3() != null)
            cliente.getTelefones().add(clienteNewDTO.getTelefone3());
    }


    @Transactional
    public Cliente insert(Cliente cliente) {
        cliente.setId(null);
        cliente = clienteRepository.save(cliente);
        enderecoRepository.saveAll(cliente.getEnderecos());
        return cliente;

    }
}
