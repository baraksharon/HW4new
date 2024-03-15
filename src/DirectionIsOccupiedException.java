public class DirectionIsOccupiedException extends RuntimeException{
    public DirectionIsOccupiedException(){}
    public DirectionIsOccupiedException(String message){super(message);}
    public DirectionIsOccupiedException(String message,Throwable cause){
            super(message, cause);
        }

}
