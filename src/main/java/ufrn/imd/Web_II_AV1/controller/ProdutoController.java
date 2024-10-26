package ufrn.imd.Web_II_AV1.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufrn.imd.Web_II_AV1.model.ProdutoEntity;
import ufrn.imd.Web_II_AV1.model.dto.ProdutoDto;
import ufrn.imd.Web_II_AV1.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping("/lista")
    public ResponseEntity<List<ProdutoEntity>> getAll(){
        List<ProdutoEntity> produtos = repository.findAll();

        if(produtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoEntity> getById(@PathVariable("id") long id){
        Optional<ProdutoEntity> clienteEntityOptional = repository.findById(id);
        return clienteEntityOptional.map(clienteEntity -> ResponseEntity.ok(clienteEntity)).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProdutoEntity> postProduto(@RequestBody ProdutoDto produtoDto){
        ProdutoEntity produto = new ProdutoEntity();
        BeanUtils.copyProperties(produtoDto,produto);
        produto = repository.save(produto);
        return ResponseEntity.ok(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoEntity> putProduto(@PathVariable("id") long id, @RequestBody ProdutoDto produtoDto){
        Optional<ProdutoEntity> produtoOptional = repository.findById(id);

        if(produtoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        ProdutoEntity produto = produtoOptional.get();
        BeanUtils.copyProperties(produtoDto,produto,"id");
        repository.save(produto);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/deleteProduto/{id}")
    public ResponseEntity<HttpStatus> deleteProduto(@PathVariable("id") long id) throws ChangeSetPersister.NotFoundException {
        Optional<ProdutoEntity> produtoOptional = repository.findById(id);

        if(produtoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/deleteLogic/{id}")
    public ResponseEntity<HttpStatus> deleteLogic(@PathVariable("id") long id){
        Optional<ProdutoEntity> produtoOptional = repository.findById(id);

        if(produtoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        ProdutoEntity produto = produtoOptional.get();
        produto.setAtivo(false);
        repository.save(produto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
