package global.coda.hms.exception;

public class WrongCredentials extends Exception{
    public WrongCredentials(String errorMessage) {
        super(errorMessage);
    }
}
