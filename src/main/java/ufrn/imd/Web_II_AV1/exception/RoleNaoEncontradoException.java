package ufrn.imd.Web_II_AV1.exception;

public class RoleNaoEncontradoException extends RuntimeException{
    public RoleNaoEncontradoException(String message){
        super(message);
    }

    public RoleNaoEncontradoException(String message, Throwable cause){
        super(message, cause);
    }
}
