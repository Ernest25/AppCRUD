package exception;
/**
 * Cette exception est déclenchée quand un objet existe déjà dans la couche de persistance.
 * et que nous voulons en ajouter un autre avec le même identifiant.
 */
public final class DuplicateKeyException extends CreateException {
}
