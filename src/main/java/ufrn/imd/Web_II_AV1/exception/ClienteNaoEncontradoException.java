package ufrn.imd.Web_II_AV1.exception;

public class ClienteNaoEncontradoException extends RuntimeException{
    public ClienteNaoEncontradoException(String message){
        super(message);
    }

    public ClienteNaoEncontradoException(String message, Throwable cause){
        super(message, cause);
    }
}
