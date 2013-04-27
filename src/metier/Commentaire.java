package metier;


/**
 * Permet à un utilisateur de commenter une Session de formation
 * 
 * @author 
 *
 */
public class Commentaire extends Entite
{
	
	
	// ======================================
    // =             Attributs             =
    // ======================================
	
	private String commentaire;
	private String dateInsertion;
	private String statutModeration;
	
	private Session session;
	private Utilisateur auteur;
	

	
	
	
	// ======================================
    // =            Constructeurs           =
    // ======================================
	
	public Commentaire(String id , String commentaire, String dateInsertion, String statutModeration) {
		super();
		setId(id);
		this.commentaire = commentaire;
		this.dateInsertion = dateInsertion;
		this.statutModeration = statutModeration;
	}

	
	public Commentaire(String id, String commentaire, String dateInsertion,
			String statutModeration, Utilisateur auteur,
			Session session) 
	{
		super();
		setId(id);
		this.commentaire = commentaire;
		this.dateInsertion = dateInsertion;
		this.statutModeration = statutModeration;
		this.auteur = auteur;
		this.session = session;
	}

	

	
	// ======================================
	// =         Getters et Setters         =
	// ======================================
		

	public String getCommentaire() {
		return commentaire;
	}


	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}


	public String getDateInsertion() {
		return dateInsertion;
	}


	public void setDateInsertion(String dateInsertion) {
		this.dateInsertion = dateInsertion;
	}


	public String getStatutModeration() {
		return statutModeration;
	}


	public void setStatutModeration(String statutModeration) {
		this.statutModeration = statutModeration;
	}


	public Utilisateur getAuteur() {
		return auteur;
	}


	public void setAuteur(Utilisateur auteur) {
		this.auteur = auteur;
	}


	public Session getSession() {
		return session;
	}


	public void setSession(Session session) {
		this.session = session;
	}


	@Override
	public String toString() {
		return "Commentaire [commentaire=" + commentaire + ", dateInsertion="
				+ dateInsertion + ", statutModeration=" + statutModeration
				+ ", auteur=" + auteur + ", session=" + session + "]";
	}

}
