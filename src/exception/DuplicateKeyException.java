package exception;
/**
 * Cette exception est d�clench�e quand un objet existe d�j� dans la couche de persistance.
 * et que nous voulons en ajouter un autre avec le m�me identifiant.
 */
public final class DuplicateKeyException extends CreateException {
}
