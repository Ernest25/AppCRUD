package metier;

import java.io.Serializable;
import java.util.HashMap;

import exception.CheckException;



/**
 * 
 * Une catégorie contient 0 ou n formations
 * 
 * @author 
 *
 */
public class Categorie extends Entite
{						
	
	// ======================================
    // =             Attributs             =
    // ======================================
	private String nom;
	private String description;
	
	private HashMap<String, Formation> formations; 
	
	
	private String imagePath; // Prévu pour donner une image icon visuel a une catégorie
	
	
	// ======================================
    // =            Constructeurs           =
    // ======================================
	public Categorie()
	{
		
	}
	
	public Categorie(final String id)
	{
		setId(id);
	}
	
	public Categorie(final String id, final String nom, final String description) 
	{
		setId(id);
		
		setNom(nom);
		setDescription(description);
		
		formations = new HashMap<String, Formation>();
	}
	
	
	// =================================================
    // =           methodes  Business (logique métier) =
    // =================================================
	 public void checkData() throws CheckException {
	        if (getNom() == null || "".equals(getNom()))
	            throw new CheckException("Invalid Nom");
	        if (getDescription() == null || "".equals(getDescription()))
	            throw new CheckException("Invalid Description");
	    }

	 
	 
	
	// ======================================
	// =         Getters et Setters        =
	// ======================================
	 
	public String getNom() 
	{
		return nom;
	}

	public void setNom(String nom) 
	{
		this.nom = nom;
	}
	
	public String getDescription() 
	{
		return description;
	}
	
	public void setDescription(String description) 
	{
		this.description = description;
	}

	
	public HashMap<String, Formation> getFormations() {
		return formations;
	}

	public void setFormations(HashMap<String, Formation> formations) {
		this.formations = formations;
	}

	@Override
	public String toString() 
	{
		return "Categorie [nom=" + nom + ", description=" + description
				+ ", formations=" + formations + "]";
	}

}
