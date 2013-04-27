package metier;

import java.io.Serializable;

/**
 * Chaque objet metier doit heriter de cette class abstraite
 *
 * @author 
 *
 */
public abstract class Entite implements Serializable
{
	// ======================================
    // =             Attributs             =
    // ======================================

    // Chaque objet du domain a un unique identifieur.
	private String id;
	
	public  String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id=id;
	}
	
}