public class NoSuchElementException extends RuntimeException {
    public NoSuchElementException(){}
    public NoSuchElementException(String message){
        super(message);
    }
    public NoSuchElementException(String message,Throwable cause){
        super(message, cause);
    }


}
