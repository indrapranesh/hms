package global.coda.hms.exception;

/**
 * Arrives when searched patient is not found .
 */
public class PatientNotFoundException extends Exception {
    /**
     * Constructor .
     *
     * @param errorMessage error
     */
    public PatientNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
