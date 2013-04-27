package metier;

import java.util.ArrayList;
import java.util.HashMap;

import exception.CheckException;


/**
 * Une session fait partie d'une et une seule formation et est décomposée en 1 ou n cours
 * 
 * @author 
 *
 */
public class Session extends Entite  
{
	// ======================================
    // =             Attributs             =
    // ======================================
	
	private String nom;
	private String description;
	private String dateDebut;
	private String dateFin;
	private int nbrPlace;
	
	private Formation formation; // appartient à 1 et 1 seule formation
	
	private HashMap<String, Cour> cours; // décomposé en 1 ou n cours
	
	private HashMap<String, Document> documents; // illustré par 0 ou n documents
	private HashMap<String, Inscription> apprenants; // suivi par 0 ou n apprenants
	private HashMap<String, Utilisateur> formateurs; // dispensé par 1 ou n formateurs
	private ArrayList<Commentaire> commentaires; // commenté par 0 ou n utilisateurs
	
	
	
	
	// ======================================
    // =            Constructeurs           =
    // ======================================
	
	public Session()
	{
		
	}
	
	public Session(final String id)
	{
		setId(id);
	}
	
	
	public Session(String id, String nom, String desc, String dateDebut, String dateFin, int nbrPlace, Formation formation) 
	{
		super();
		setId(id);
		this.nom = nom;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nbrPlace = nbrPlace;
		this.formation = formation;
	}

	public Session(String nom, String desc, String dateDebut, String dateFin,
			int nbrPlace, Formation formation, HashMap<String, Cour> cours,
			HashMap<String, Document> documents,
			HashMap<String, Inscription> apprenants,
			HashMap<String, Utilisateur> formateurs,
			ArrayList<Commentaire> commentaires) 
	{
		super();
		this.nom = nom;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nbrPlace = nbrPlace;
		this.formation = formation;
		this.cours = cours;
		this.documents = documents;
		this.apprenants = apprenants;
		this.formateurs = formateurs;
		this.commentaires = commentaires;
	}
	
	
	// ======================================
    // =           Business methods         =
    // ======================================
    public void checkData() throws CheckException {
        if (getNom() == null || "".equals(getNom()))
            throw new CheckException("Invalid name");
        if (getDescription() == null || "".equals(getDescription()))
            throw new CheckException("Invalid description");
        if (getFormation() == null || getFormation().getId() == null || "".equals(getFormation().getId()))
            throw new CheckException("Invalid Formation");
    }
	
	// ======================================
	// =         Getters et Setters         =
	// ======================================
			 

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public int getNbrPlace() {
		return nbrPlace;
	}

	public void setNbrPlace(int nbrPlace) {
		this.nbrPlace = nbrPlace;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public HashMap<String, Cour> getCours() {
		return cours;
	}

	public void setCours(HashMap<String, Cour> cours) {
		this.cours = cours;
	}

	public HashMap<String, Document> getDocuments() {
		return documents;
	}

	public void setDocuments(HashMap<String, Document> documents) {
		this.documents = documents;
	}

	public HashMap<String, Inscription> getApprenants() {
		return apprenants;
	}

	public void setApprenants(HashMap<String, Inscription> apprenants) {
		this.apprenants = apprenants;
	}

	public HashMap<String, Utilisateur> getFormateurs() {
		return formateurs;
	}

	public void setFormateurs(HashMap<String, Utilisateur> formateurs) {
		this.formateurs = formateurs;
	}

	public ArrayList<Commentaire> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(ArrayList<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}

	@Override
	public String toString() {
		return "SessionFormation [nom=" + nom + ", dateDebut=" + dateDebut
				+ ", dateFin=" + dateFin + ", nbrPlace=" + nbrPlace
				+ ", formation=" + formation + ", cours=" + cours
				+ ", documents=" + documents + ", apprenants=" + apprenants
				+ ", formateurs=" + formateurs + ", commentaires="
				+ commentaires + "]";
	}

}
