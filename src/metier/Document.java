package metier;

import java.util.HashMap;



/**
 * 
 * Un document est composé de méta-informations d'un document situé "physiquement" à une URL donnée
 * 
 * Il illustre une et une seule Session. 
 * Il peut-être téléchargé par 0 ou plusieurs utilisateurs. 
 * Mais est déposé par un et un seul Utilisateur qui en est l'auteur
 * 
 * @author 
 *
 */
public class Document extends Entite  
{
	// ======================================
    // =             Attributs             =
    // ======================================
	
	private String titre;
	private String format;
	private String dateCreation;
	private String dateModifcation;
	private String url;
	private String description;
	private int nbrTelechargements;
	
	private Session session; // illustre 1 et 1 seule session de formation
	private Utilisateur auteur; // déposé par 1 et 1 seul auteur (utilisateur formateur)
	
	private HashMap<String, Utilisateur> utilisateursTelecharge; // téléchargé par 0 ou n utilisateurs
	
	
	
	
	
	// ======================================
    // =            Constructeurs           =
    // ======================================
	
	public Document()
	{
		
	}
	
	public Document(final String id, String titre, String format, String dateCreation, String dateModifcation, String url, String description, int nbTelechargement, Session session, Utilisateur auteur)
	{
		setId(id);
		setTitre(titre);
		setFormat(format);
		setDateCreation(dateCreation);
		setDateModifcation(dateModifcation);
		setUrl(url);
		setDescription(description);
		setNbrTelechargements(nbTelechargement);
		setSession(session);
		setAuteur(auteur);
		
		utilisateursTelecharge = new HashMap<String, Utilisateur>();
	}
	
	
	
	
	// ======================================
	// =         Getters et Setters         =
	// ======================================
		 

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getDateModifcation() {
		return dateModifcation;
	}

	public void setDateModifcation(String dateModifcation) {
		this.dateModifcation = dateModifcation;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNbrTelechargements() {
		return nbrTelechargements;
	}

	public void setNbrTelechargements(int nbrTelechargements) {
		this.nbrTelechargements = nbrTelechargements;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Utilisateur getAuteur() {
		return auteur;
	}

	public void setAuteur(Utilisateur auteur) {
		this.auteur = auteur;
	}

	public HashMap<String, Utilisateur> getUtilisateursTelecharge() {
		return utilisateursTelecharge;
	}

	public void setUtilisateursTelecharge(
			HashMap<String, Utilisateur> utilisateursTelecharge) {
		this.utilisateursTelecharge = utilisateursTelecharge;
	}

	@Override
	public String toString() {
		return "Document [titre=" + titre + ", format=" + format
				+ ", dateCreation=" + dateCreation + ", dateModifcation="
				+ dateModifcation + ", url=" + url + ", description="
				+ description + ", nbrTelechargements=" + nbrTelechargements
				+ ", session=" + session + ", auteur=" + auteur
				+ ", utilisateursTelecharge=" + utilisateursTelecharge + "]";
	}
}
