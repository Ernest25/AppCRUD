package exception;
/**
 * Cette exception est d�clench�e quand un objet ne peut-�tre mis a jour.
 */
public final class UpdateException extends ApplicationException {

    public UpdateException(final String message) {
        super(message);
    }
}
