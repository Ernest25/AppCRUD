package exception;
/**
 * Cette exception est déclenchée quand une verification de la  erreur de validation est trouvée.
 */
public final class CheckException extends ApplicationException {

    public CheckException(final String message) {
        super(message);
    }
}
