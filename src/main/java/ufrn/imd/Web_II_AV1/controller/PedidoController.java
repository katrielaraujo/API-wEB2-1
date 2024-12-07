package ufrn.imd.Web_II_AV1.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufrn.imd.Web_II_AV1.exception.PedidoNaoEncontradoException;
import ufrn.imd.Web_II_AV1.model.dto.PedidoDto;
import ufrn.imd.Web_II_AV1.model.dto.ProdutoDto;
import ufrn.imd.Web_II_AV1.service.PedidoService;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/lista")
    public ResponseEntity<List<PedidoDto>> getAll(){
        List<PedidoDto> pedidos = pedidoService.findAll();
        if(pedidos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> getById(@PathVariable Long id){
        return pedidoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PedidoDto> postPedido(@Valid @RequestBody PedidoDto pedidoDto){
        PedidoDto newPedido = pedidoService.save(pedidoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDto> putPedido(@PathVariable Long id, @Valid @RequestBody PedidoDto pedidoDto){
        return pedidoService.update(id, pedidoDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id){
        if(pedidoService.deleteById(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/deleteLogic")
    public ResponseEntity<PedidoDto> logicalDelete(@PathVariable Long id){
        return pedidoService.logicalDelete(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/adicionarProduto")
    public ResponseEntity<PedidoDto> adicionarProduto(@PathVariable Long id, @Valid @RequestBody ProdutoDto produtoDto){
        return pedidoService.adicionarProduto(id, produtoDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/removerProduto")
    public ResponseEntity<PedidoDto> removerProduto(@PathVariable Long id,@Valid @RequestBody ProdutoDto prdutoDto){
        return pedidoService.removerProduto(id, prdutoDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
