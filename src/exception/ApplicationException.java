package exception;
/**
 * Cette exception abstraite  est la superclass de toutes les exceptions de l'application.
 * C'est une "checked exception" parce qu'elle etends Exception.
 */
public abstract class ApplicationException extends RuntimeException  {

    protected ApplicationException() 
    {
    
    }

    protected ApplicationException(final String message) {
        super(message);
    }
}
