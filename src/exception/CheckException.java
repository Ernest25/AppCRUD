package exception;
/**
 * Cette exception est d�clench�e quand une verification de la  erreur de validation est trouv�e.
 */
public final class CheckException extends ApplicationException {

    public CheckException(final String message) {
        super(message);
    }
}
