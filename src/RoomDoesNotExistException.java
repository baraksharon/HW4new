public class RoomDoesNotExistException extends RuntimeException{

    public RoomDoesNotExistException(){}

    public RoomDoesNotExistException(String message){
        super(message);
    }

    public RoomDoesNotExistException(String message,Throwable cause){
        super(message, cause);
    }

}
