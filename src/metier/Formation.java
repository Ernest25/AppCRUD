package metier;

import java.util.HashMap;

import exception.CheckException;



/**
 * 
 * Une formation appartient à une et une seule catégorie et se décline en 1 ou n sessions
 * 
 * @author 
 *
 */
public class Formation extends Entite 
{
	
	// ======================================
    // =             Attributs             =
    // ======================================
	
	
	private String libelle;
	private String preRequis;
	private String description;
	private double prix;
	
	private HashMap<Integer, Session> sessions; // se décline en 1 ou n sessions
	
	private Categorie categorie; // appartient à 1 et 1 seule catégorie
	
	private String imagePath; // Prévu pour donner une image icon visuel a une Formation
	
	
	// ======================================
    // =            Constructeurs            =
    // ======================================
	public Formation()
	{
		
	}
	
	public Formation(final String id)
	{
		setId(id);
	}
	
	public Formation(String idFormation, String libelle, String preRequis, String description, double prix, Categorie categorie)
	{
		setId(idFormation);
		
		setLibelle(libelle);
		setPreRequis(preRequis);
		setDescription(description);
		setPrix(prix);
		
		setCategorie(categorie);
		
		sessions = new HashMap<Integer, Session>();
	}
	
	
	
	// ======================================================
    // =           methodes  Business (logique métier)      =
    // ======================================================
	
	public void checkData() throws CheckException {
        if (getLibelle() == null || "".equals(getLibelle()))
            throw new CheckException("Invalid Nom");
        if (getPrix() <= 0)
            throw new CheckException("Invalid Prix");
        if (getCategorie() == null || getCategorie().getId() == null || "".equals(getCategorie().getId()))
            throw new CheckException("Invalid Categorie");
    }
    
    
    
    // ======================================
    // =         Getters et Setters         =
    // ======================================

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getPreRequis() {
		return preRequis;
	}

	public void setPreRequis(String preRequis) {
		this.preRequis = preRequis;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public HashMap<Integer, Session> getSessions() {
		return sessions;
	}

	public void setSessions(HashMap<Integer, Session> sessions) {
		this.sessions = sessions;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	@Override
	public String toString() {
		return "Formation [libelle=" + libelle + ", preRequis=" + preRequis
				+ ", description=" + description + ", prix=" + prix
				+ ", sessions=" + sessions + ", categorie=" + categorie + "]";
	}
}