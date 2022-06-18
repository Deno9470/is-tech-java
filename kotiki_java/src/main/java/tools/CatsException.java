package tools;

public class CatsException extends Exception {
    public CatsException() {
    }

    public CatsException(String message) {
        super(message);
    }

    public CatsException(String message, Exception innerException) {
        super(message, innerException);
    }
}


