package ufrn.imd.Web_II_AV1.exception;

public class ProdutoNaoEncontradoException extends RuntimeException{

    public ProdutoNaoEncontradoException(String message){
        super(message);
    }

    public ProdutoNaoEncontradoException(String message, Throwable cause){
        super(message, cause);
    }
}
