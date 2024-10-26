package ufrn.imd.Web_II_AV1.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufrn.imd.Web_II_AV1.model.ClienteEntity;

import ufrn.imd.Web_II_AV1.model.dto.ClienteDto;
import ufrn.imd.Web_II_AV1.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @GetMapping("/lista")
    public ResponseEntity<List<ClienteEntity>> getAll(){
        List<ClienteEntity> clientes = repository.findAll();

        if(clientes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteEntity> getById(@PathVariable("id") long id){
        Optional<ClienteEntity> clienteOptional = repository.findById(id);

        return clienteOptional.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping
    public ResponseEntity<ClienteEntity> postCliente(@RequestBody ClienteDto clienteDto){
        ClienteEntity cliente = new ClienteEntity();
        BeanUtils.copyProperties(clienteDto,cliente);
        cliente = repository.save(cliente);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteEntity> putCliente(@PathVariable("id") long id,@RequestBody ClienteDto clienteDto){
        Optional<ClienteEntity> clienteOptional = repository.findById(id);

        if(clienteOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        ClienteEntity cliente = clienteOptional.get();
        BeanUtils.copyProperties(clienteDto,cliente,"id","ativo");
        repository.save(cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/deleteCliente/{id}")
    public ResponseEntity<HttpStatus> deleteCliente(@PathVariable("id") long id){
        Optional<ClienteEntity> clienteOptional = repository.findById(id);

        if(clienteOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/deleteLogic/{id}")
    public ResponseEntity<HttpStatus> deleteLogic(@PathVariable("id") long id){
        Optional<ClienteEntity> clienteOptional = repository.findById(id);

        if(clienteOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ClienteEntity cliente = clienteOptional.get();
        cliente.setAtivo(false);
        repository.save(cliente);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
