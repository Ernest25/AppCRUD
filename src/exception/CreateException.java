package exception;
/**
 * Cette exception est déclenchée quand un objet ne peut-être créé.
 */
public class CreateException extends ApplicationException {

    public CreateException() {
    }

    public CreateException(final String message) {
        super(message);
    }
}
