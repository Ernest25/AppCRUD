package exception;
/**
 * Cette exception est d�clench�e quand un objet ne peut-�tre cr��.
 */
public class CreateException extends ApplicationException {

    public CreateException() {
    }

    public CreateException(final String message) {
        super(message);
    }
}
