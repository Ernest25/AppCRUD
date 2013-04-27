package metier;

import java.util.HashMap;


public class Cour extends Entite
{
	
	// ======================================
    // =             Attributs             =
    // ======================================
	
	private String nomMatiere;
	private String description;
	
	private String date;
	private String heureDebut;
	private String heureFin;
	private String adresse;
	private String salle;
	
	private Session sessionForm; // appartient à 1 et 1 seule session de formation
	
	private HashMap<Integer, Utilisateur> apprenants; // suivi par 0 ou n utilisateurs apprenants
	
	
	private String imagePath; // Prévu pour donner une image icon visuel a un Cour
	
	
	// ======================================
    // =            Constructeurs           =
    // ======================================
	
	public Cour()
	{
		
	}
	
	public Cour(final String id)
	{
		setId(id);
	}
	
	public Cour(String id, String date, String heureDebut, String heureFin,
			String adresse, String salle, Session sessionForm) 
	{
		super();
		
		setId(id);
		this.date = date;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.adresse = adresse;
		this.salle = salle;
		this.sessionForm = sessionForm;
		
		apprenants = new HashMap<>();
	}

	public Cour(String id, String date, String heureDebut, String heureFin,
			String adresse, String salle, Session sessionForm,
			HashMap<Integer, Utilisateur> apprenants) 
	{
		super();
		
		setId(id);
		this.date = date;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.adresse = adresse;
		this.salle = salle;
		this.sessionForm = sessionForm;
		this.apprenants = apprenants;
	}

	
	
	// ======================================
	// =         Getters et Setters         =
	// ======================================
	

	public String getNomMatiere() {
		return nomMatiere;
	}

	public void setNomMatiere(String nomMatiere) {
		this.nomMatiere = nomMatiere;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
			 
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(String heureDebut) {
		this.heureDebut = heureDebut;
	}

	public String getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(String heureFin) {
		this.heureFin = heureFin;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getSalle() {
		return salle;
	}

	public void setSalle(String salle) {
		this.salle = salle;
	}

	public Session getSessionForm() {
		return sessionForm;
	}

	public void setSessionForm(Session sessionForm) {
		this.sessionForm = sessionForm;
	}

	public HashMap<Integer, Utilisateur> getApprenants() {
		return apprenants;
	}

	public void setApprenants(HashMap<Integer, Utilisateur> apprenants) {
		this.apprenants = apprenants;
	}
	

	@Override
	public String toString() {
		return "Cour [date=" + date + ", heureDebut=" + heureDebut
				+ ", heureFin=" + heureFin + ", adresse=" + adresse
				+ ", salle=" + salle + ", sessionForm=" + sessionForm
				+ ", apprenants=" + apprenants + "]";
	}

}
