package ufrn.imd.Web_II_AV1.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufrn.imd.Web_II_AV1.exception.PedidoNaoEncontradoException;
import ufrn.imd.Web_II_AV1.model.PedidoEntity;
import ufrn.imd.Web_II_AV1.model.ProdutoEntity;
import ufrn.imd.Web_II_AV1.model.dto.PedidoDto;
import ufrn.imd.Web_II_AV1.model.dto.ProdutoDto;
import ufrn.imd.Web_II_AV1.repository.PedidoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ModelMapper modelMapper;

    public List<PedidoDto> findAll(){
        return pedidoRepository.findAllByAtivoTrue().stream()
                .map(pedido -> modelMapper.map(pedido, PedidoDto.class))
                .toList();
    }

    public Optional<PedidoDto> findById(Long id){
        return pedidoRepository.findByIdAndAtivoTrue(id)
                .map(pedido -> modelMapper.map(pedido, PedidoDto.class));
    }

    public Optional<PedidoDto> adicionarProduto(Long id, ProdutoDto produtoDto){
        return pedidoRepository.findByIdAndAtivoTrue(id)
                .map(pedido -> {
                    ProdutoDto savedProdutoDto = produtoService.save(produtoDto);
                    ProdutoEntity produto = modelMapper.map(savedProdutoDto, ProdutoEntity.class);
                    pedido.getProdutos().add(produto);
                    PedidoEntity savedPedido = pedidoRepository.save(pedido);
                    return modelMapper.map(savedPedido, PedidoDto.class);
                });
    }

    public Optional<PedidoDto> removerProduto(Long id, ProdutoDto produtoDto){
        return pedidoRepository.findByIdAndAtivoTrue(id)
                .map(pedido -> {
                    ProdutoEntity produto = modelMapper.map(produtoDto, ProdutoEntity.class);
                    pedido.getProdutos().removeIf(p -> p.getId().equals(produto.getId()));
                    PedidoEntity savedPedido = pedidoRepository.save(pedido);
                    return modelMapper.map(savedPedido, PedidoDto.class);
                });
    }

    public PedidoDto save(PedidoDto pedidoDto){
        PedidoEntity pedido = modelMapper.map(pedidoDto, PedidoEntity.class);
        PedidoEntity savedPedido = pedidoRepository.save(pedido);
        return modelMapper.map(savedPedido, PedidoDto.class);
    }

    public Optional<PedidoDto> update(Long id, PedidoDto pedidoDto){
        return pedidoRepository.findByIdAndAtivoTrue(id)
                .map(pedido -> {
                   try {
                       PedidoEntity updatedPedido = modelMapper.map(pedidoDto, PedidoEntity.class);
                       updatedPedido.setId(pedido.getId());
                       return modelMapper.map(pedidoRepository.save(updatedPedido), PedidoDto.class);
                   } catch (Exception e){
                       throw new PedidoNaoEncontradoException("Pedido com ID "+ id + " não encontrado");
                   }
                });
    }

    public boolean deleteById(Long id){
        if(pedidoRepository.existsById(id)){
            pedidoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<PedidoDto> logicalDelete(Long id){
        return pedidoRepository.findByIdAndAtivoTrue(id)
                .map(pedido -> {
                    pedido.setAtivo(false);
                    return modelMapper.map(pedidoRepository.save(pedido), PedidoDto.class);
                });
    }
}
