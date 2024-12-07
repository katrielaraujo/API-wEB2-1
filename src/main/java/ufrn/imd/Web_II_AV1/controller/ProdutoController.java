package ufrn.imd.Web_II_AV1.controller;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufrn.imd.Web_II_AV1.model.ProdutoEntity;
import ufrn.imd.Web_II_AV1.model.dto.ClienteDto;
import ufrn.imd.Web_II_AV1.model.dto.ProdutoDto;
import ufrn.imd.Web_II_AV1.repository.ProdutoRepository;
import ufrn.imd.Web_II_AV1.service.ProdutoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/lista")
    public ResponseEntity<List<ProdutoDto>> getAll(){
        List<ProdutoDto> produtos = produtoService.findAll();

        if(produtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> getById(@PathVariable("id") long id){
        return produtoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> postProduto(@Valid @RequestBody ProdutoDto produtoDto){
       ProdutoDto newProduto = produtoService.save(produtoDto);
       return ResponseEntity.status(HttpStatus.CREATED).body(newProduto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDto> putProduto(@PathVariable("id") long id,@Valid @RequestBody ProdutoDto produtoDto){
        return produtoService.update(id, produtoDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deleteProduto/{id}")
    public ResponseEntity<HttpStatus> deleteProduto(@PathVariable("id") long id) throws ChangeSetPersister.NotFoundException {
       if(produtoService.deleteById(id)){
           return ResponseEntity.noContent().build();
       }
       return ResponseEntity.notFound().build();
    }

    @PatchMapping("/deleteLogic/{id}")
    public ResponseEntity<HttpStatus> deleteLogic(@PathVariable("id") long id){
        Optional<ProdutoDto> produtoOptional = produtoService.findById(id);

        if(produtoOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ProdutoDto produto = produtoOptional.get();
        produtoService.save(produto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
