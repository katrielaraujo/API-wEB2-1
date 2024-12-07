package ufrn.imd.Web_II_AV1.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufrn.imd.Web_II_AV1.exception.ProdutoNaoEncontradoException;
import ufrn.imd.Web_II_AV1.model.ProdutoEntity;
import ufrn.imd.Web_II_AV1.model.dto.ProdutoDto;
import ufrn.imd.Web_II_AV1.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ProdutoDto> findAll(){
        return produtoRepository.findAllByAtivoTrue().stream()
                .map(produtoEntity -> modelMapper.map(produtoEntity,ProdutoDto.class))
                .toList();
    }

    public Optional<ProdutoDto> findById(Long id){
        return produtoRepository.findByIdAndAtivoTrue(id)
                .map(produtoEntity -> modelMapper.map(produtoEntity, ProdutoDto.class));
    }

    public ProdutoDto save(ProdutoDto produtoDto){
        ProdutoEntity produto = modelMapper.map(produtoDto, ProdutoEntity.class);
        ProdutoEntity savedProduto = produtoRepository.save(produto);
        return modelMapper.map(savedProduto, ProdutoDto.class);
    }

    public Optional<ProdutoDto> update(Long id, ProdutoDto produtoDto){
        return produtoRepository.findByIdAndAtivoTrue(id)
                .map(produto -> {
                    try {
                        ProdutoEntity updatedProduto = modelMapper.map(produtoDto, ProdutoEntity.class);
                        updatedProduto.setId(produto.getId());
                        return modelMapper.map(produtoRepository.save(updatedProduto), ProdutoDto.class);
                    } catch (Exception e) {
                        throw new ProdutoNaoEncontradoException("Produto com ID "+ id + " não encontrado");
                    }
                });
    }

    public boolean deleteById(Long id){
        if(produtoRepository.existsById(id)){
            produtoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public ProdutoDto logicalDelete(Long id){
       return produtoRepository.findByIdAndAtivoTrue(id)
               .map(produto -> {
                   produto.setAtivo(false);
                   return modelMapper.map(produtoRepository.save(produto), ProdutoDto.class);
               })
               .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto com ID "+ id + " não encontrado"));
    }
}
