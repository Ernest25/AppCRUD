package metier;


/**
 * 
 * Permet de connaitre si le payement a été éffectué par un apprenant pour une session de formation donnée
 * 
 * @author 
 *
 */
public class Inscription extends Entite {

	
	// ======================================
    // =             Attributs             =
    // ======================================
	
	private boolean paiement;
	private double cout;
	
	private Session session; // à une session s'inscrivent 0 ou n utilisateurs apprenants
	private Utilisateur apprenant; // un apprenant s'inscrit à 0 ou n  sessions de formation 
	
	
	
	
	// ======================================
    // =            Constructeurs           =
    // ======================================*
	
	public Inscription(String idUtilisateur,  boolean paiement, double cout, Session session, Utilisateur apprenant) 
	{
		super();
		setId(idUtilisateur);
		this.paiement = paiement;
		this.cout = cout;
		this.session = session;
		this.apprenant = apprenant;
	}



	// ======================================
	// =         Getters et Setters         =
	// ======================================
	
	

	public boolean isPaiement() {
		return paiement;
	}




	public void setPaiement(boolean paiement) {
		this.paiement = paiement;
	}




	public double getCout() {
		return cout;
	}




	public void setCout(double cout) {
		this.cout = cout;
	}




	public Session getSession() {
		return session;
	}




	public void setSession(Session session) {
		this.session = session;
	}




	public Utilisateur getApprenant() {
		return apprenant;
	}




	public void setApprenant(Utilisateur apprenant) {
		this.apprenant = apprenant;
	}



	@Override
	public String toString() {
		return "Inscription [paiement=" + paiement + ", cout=" + cout
				+ ", session=" + session + ", apprenant=" + apprenant + "]";
	}
	
				 
}
