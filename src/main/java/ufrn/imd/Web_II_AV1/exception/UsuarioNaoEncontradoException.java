package ufrn.imd.Web_II_AV1.exception;

public class UsuarioNaoEncontradoException extends RuntimeException{
    public UsuarioNaoEncontradoException(String message){
        super(message);
    }

    public UsuarioNaoEncontradoException(String message, Throwable cause){
        super(message, cause);
    }
}
