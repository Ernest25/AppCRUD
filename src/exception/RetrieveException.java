package exception;
/**
 * Cett exception est d�clench�e quand un objet ne peut-�tre trouv�
 */
public class RetrieveException extends ApplicationException {

    public RetrieveException() {
    }

    public RetrieveException(final String message) {
        super(message);
    }
}
