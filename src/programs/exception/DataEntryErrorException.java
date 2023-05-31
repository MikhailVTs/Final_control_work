package exception;

public class DataEntryErrorException extends RuntimeException{
    
    public DataEntryErrorException (String msg) {
        super(msg);
    }
}