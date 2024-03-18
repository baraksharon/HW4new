public class ExitIsOccupiedException extends RuntimeException{

    public ExitIsOccupiedException(){}

    public ExitIsOccupiedException(String message){
        super(message);
    }

    public ExitIsOccupiedException(String message,Throwable cause){
        super(message, cause);
    }

}
