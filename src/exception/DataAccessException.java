package exception;
/**
 * Cette exception est d�clmench�e quand un probl�me general survient dans la couche  persistance.
 */
public final class DataAccessException extends RuntimeException {

    public DataAccessException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
