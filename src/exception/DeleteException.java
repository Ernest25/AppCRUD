package exception;
/**
 * Cette exception est d�clench�e quand un objet ne peut-�tre supprim�
 */
public final class DeleteException extends ApplicationException {

    public DeleteException(final String message) {
        super(message);
    }
}
