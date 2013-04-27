package exception;
/**
 * Cette exception est déclenchée quand un objet ne peut-être mis a jour.
 */
public final class UpdateException extends ApplicationException {

    public UpdateException(final String message) {
        super(message);
    }
}
