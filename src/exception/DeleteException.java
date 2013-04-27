package exception;
/**
 * Cette exception est déclenchée quand un objet ne peut-être supprimé
 */
public final class DeleteException extends ApplicationException {

    public DeleteException(final String message) {
        super(message);
    }
}
