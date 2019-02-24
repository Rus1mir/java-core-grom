package lesson35.exception;

public class DataFormatErrorException extends Exception {

    public DataFormatErrorException(String message) {
        super(message);
    }

    public DataFormatErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
