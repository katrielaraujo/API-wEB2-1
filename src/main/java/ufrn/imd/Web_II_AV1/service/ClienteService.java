package ufrn.imd.Web_II_AV1.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import ufrn.imd.Web_II_AV1.exception.ClienteNaoEncontradoException;
import ufrn.imd.Web_II_AV1.model.ClienteEntity;
import ufrn.imd.Web_II_AV1.model.dto.ClienteDto;
import ufrn.imd.Web_II_AV1.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ClienteDto> findAll(){
        return clienteRepository.findAllByAtivoTrue().stream()
                .map(cliente -> modelMapper.map(cliente, ClienteDto.class))
                .toList();
    }

    public Optional<ClienteDto> findById(Long id){
        return clienteRepository.findByIdAndAtivoTrue(id)
                .map(cliente -> modelMapper.map(cliente, ClienteDto.class));
    }

    public ClienteDto save(ClienteDto clienteDto){
        ClienteEntity cliente = modelMapper.map(clienteDto, ClienteEntity.class);
        ClienteEntity savedCliente = clienteRepository.save(cliente);
        return modelMapper.map(savedCliente, ClienteDto.class);
    }

    public Optional<ClienteDto> update(Long id, ClienteDto clienteDto){
        return clienteRepository.findByIdAndAtivoTrue(id)
                .map(cliente -> {
                    try {
                        ClienteEntity updatedCliente = modelMapper.map(clienteDto, ClienteEntity.class);
                        updatedCliente.setId(cliente.getId());
                        return modelMapper.map(clienteRepository.save(updatedCliente), ClienteDto.class);
                    } catch (Exception e){
                        throw new ClienteNaoEncontradoException("Pedido com ID "+ id + " não encontrado");
                    }
                });
    }

    public boolean deleteById(Long id){
        if(clienteRepository.existsById(id)){
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public ClienteDto logicalDelete(Long id){
       return clienteRepository.findByIdAndAtivoTrue(id)
               .map(cliente -> {
                   cliente.setAtivo(false);
                   return modelMapper.map(clienteRepository.save(cliente), ClienteDto.class);
               })
               .orElseThrow(() -> new ClienteNaoEncontradoException("Pedido com ID "+ id + " não encontrado"));
    }
}
