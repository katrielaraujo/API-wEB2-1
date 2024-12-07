package ufrn.imd.Web_II_AV1.exception;

public class PedidoNaoEncontradoException extends RuntimeException{
    public PedidoNaoEncontradoException(String message){
        super(message);
    }

    public PedidoNaoEncontradoException(String message, Throwable cause){
        super(message, cause);
    }
}
