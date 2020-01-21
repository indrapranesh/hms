package global.coda.hms.exception;

/**
 * exception arrives when searched doctor not found .
 */
public class DoctorNotFoundException extends Exception {
    /**
     * Constructor .
     *
     * @param msg error
     */
    public DoctorNotFoundException(String msg) {
        super(msg);
    }
}
