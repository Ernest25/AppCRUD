package dao;

import exception.DuplicateKeyException;
import exception.ObjectNotFoundException;




/**
 * This class manages unique IDs for Database.
 * It follows the Singleton design pattern.
 */
public final class UniqueIdGenerator {

    // ======================================
    // =             Attributes             =
    // ======================================
    // A handle to the unique Singleton instance.
    private static UniqueIdGenerator instance = null;
    
    private final UniqueIdGeneratorDAO dao = new UniqueIdGeneratorDAO();

    // Used for logging
    private final String cname = this.getClass().getName();

    // ======================================
    // =            Constructors            =
    // ======================================
    /**
     * The constructor is made private to prevent others from instantiating this class.
     */
    private UniqueIdGenerator() {
    }

    // ======================================
    // =           Business methods         =
    // ======================================
    /**
     * This method is used by other classes to get a unique instance of the UniqueIdGenerator
     *
     * @return The unique instance of this class.
     */
    public static UniqueIdGenerator getInstance() {
        if (null == instance) {
            instance = new UniqueIdGenerator();
        }
        return instance;
    }

    /**
     * This method returns a unique id for a given name. For example if you call this
     * method like this : getUniqueId("order") it will return the unique id for an order
     *
     * @param name name of the entity that we want to have a unique id
     * @return the unique id
     */
    public String getUniqueId(final String name) {
        final String mname = "getUniqueId";

        int nextId = 0;

        try {

            // Gets the current value
            nextId = dao.select(name) + 1;
            // Updates the value with the new id
            dao.update(name, nextId);

        } catch (ObjectNotFoundException e) {

            // The value doesn't exist, we have to create it and we return the number 1
            try {
                dao.insert(name);
                nextId = 1;
            } catch (DuplicateKeyException e1) {
                // Duplicate key exception cannot append
            }
        }

        return String.valueOf(nextId);
    }
}
