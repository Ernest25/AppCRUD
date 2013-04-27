package exception;
/**
 * Cett exception est déclenchée quand un objet ne peut-être trouvé
 */
public class RetrieveException extends ApplicationException {

    public RetrieveException() {
    }

    public RetrieveException(final String message) {
        super(message);
    }
}
