package ufrn.imd.Web_II_AV1.controller;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufrn.imd.Web_II_AV1.model.ClienteEntity;

import ufrn.imd.Web_II_AV1.model.dto.ClienteDto;
import ufrn.imd.Web_II_AV1.repository.ClienteRepository;
import ufrn.imd.Web_II_AV1.service.ClienteService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/lista")
    public ResponseEntity<List<ClienteDto>> getAll(){
        List<ClienteDto> clientes = clienteService.findAll();

        if(clientes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getById(@PathVariable("id") long id){
        Optional<ClienteDto> clienteOptional = clienteService.findById(id);

        return clienteOptional.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ClienteDto> postCliente(@Valid @RequestBody ClienteDto clienteDto){
        ClienteDto cliente = clienteService.save(clienteDto);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> putCliente(@PathVariable("id") long id,@Valid @RequestBody ClienteDto clienteDto){
        return clienteService.update(id, clienteDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deleteCliente/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable("id") long id){
        if(clienteService.deleteById(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/deleteLogic/{id}")
    public ResponseEntity<HttpStatus> deleteLogic(@PathVariable("id") long id){
        Optional<ClienteDto> clienteOptional = clienteService.findById(id);

        if(clienteOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ClienteDto cliente = clienteOptional.get();
        clienteService.save(cliente);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
