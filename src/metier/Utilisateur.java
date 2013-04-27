package metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import exception.CheckException;


/**
 * 
 * 
 * @author 
 *
 */
public class Utilisateur extends Entite implements Serializable
{					
	// ======================================
    // =             Attributs              =
    // ======================================
	
	private String login;
	private String motDePasse;
	private String nom;
	private String prenom;
	private String mail;
	private String adresse;
	private String codePostal;
	private String ville;
	private String telephoneFixe;
	private String telephonePortable;
	private String datePremiereInscription;
	
	private String typeUtilisateur; // prend la valeur soit formateur soit apprenant

	private HashMap<String, Cour> cours; // assiste à 0 ou n cours
	private HashMap<String, Commentaire> commentaires; // dépose 0 ou n commentaires
	
	
	
	// ======================================
    // =            Constructeurs           =
    // ======================================
	
	public Utilisateur(final String login)
	{
		setId(login);
		this.login = login;
	}
	
	public Utilisateur(String login, String motDePasse, String nom,
			String prenom, String mail, String adresse, String codePostal,
			String ville, String telephoneFixe, String telephonePortable,
			String datePremiereInscription, String typeUtilisateur) 
	{
		super();
		setId(login);
		this.login = login; // le login c'est l'ID
		this.motDePasse = motDePasse;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.telephoneFixe = telephoneFixe;
		this.telephonePortable = telephonePortable;
		this.datePremiereInscription = datePremiereInscription;
		this.typeUtilisateur = typeUtilisateur;
	}

	
	public Utilisateur(String login, String motDePasse, String nom,
			String prenom, String mail, String adresse, String codePostal,
			String ville, String telephoneFixe, String telephonePortable,
			String datePremiereInscription, String typeUtilisateur,
			HashMap<String, Cour> cours,
			HashMap<String, Commentaire> commentaires) 
	{
		super();
		setId(login);
		this.login = login;
		this.motDePasse = motDePasse;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.telephoneFixe = telephoneFixe;
		this.telephonePortable = telephonePortable;
		this.datePremiereInscription = datePremiereInscription;
		this.typeUtilisateur = typeUtilisateur;
		this.cours = cours;
		this.commentaires = commentaires;
	}


	 // ======================================
    // =           Business methods         =
    // ======================================
    /**
     * Cette  methode check l' integrité odes données de l'objet.
     *
     * @throws CheckException si les données sont invalides
     */
    public void checkData() throws CheckException {
        if (getLogin() == null || "".equals(getLogin()))
            throw new CheckException("Invalid customer first name");
        if (getMotDePasse() == null || "".equals(getMotDePasse()))
            throw new CheckException("Invalid customer last name");
    }

    /**
     * verifie le password d'un utilisateur
     *
     * @param password
     * @throws CheckException thrown si le password est vide ou different de celui stocké dans la base
     *                     
     */
    public void matchPassword(String password) throws CheckException {
        if (password == null || "".equals(password))
            throw new CheckException("Invalid password");

        // Le password enteré par l'utilisateur n'est pas le même que celui de la  database
        if (!password.equals(getMotDePasse()))
            throw new CheckException("Password doesn't match");
    }
	
	
	// ======================================
	// =         Getters et Setters         =
	// ======================================
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getTelephoneFixe() {
		return telephoneFixe;
	}

	public void setTelephoneFixe(String telephoneFixe) {
		this.telephoneFixe = telephoneFixe;
	}

	public String getTelephonePortable() {
		return telephonePortable;
	}

	public void setTelephonePortable(String telephonePortable) {
		this.telephonePortable = telephonePortable;
	}

	public String getDatePremiereInscription() {
		return datePremiereInscription;
	}

	public void setDatePremiereInscription(String datePremiereInscription) {
		this.datePremiereInscription = datePremiereInscription;
	}

	public String getTypeUtilisateur() {
		return typeUtilisateur;
	}

	public void setTypeUtilisateur(String typeUtilisateur) {
		this.typeUtilisateur = typeUtilisateur;
	}

	public HashMap<String, Cour> getCours() {
		return cours;
	}

	public void setCours(HashMap<String, Cour> cours) {
		this.cours = cours;
	}

	public HashMap<String, Commentaire> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(HashMap<String, Commentaire> commentaires) {
		this.commentaires = commentaires;
	}


	
	
	// ======================================
	// =         Classe internet            =
	// ======================================

	/**
	 * Cette classe interne permet de spécifier le type d'utilisateur sachant qu'un utilisateur est d'un et un seul type
	 * 
	 * @author 
	 *
	 */
    static class  TypeUtilisateur 
	{
    	static ArrayList<String> valeurs = new ArrayList<>();
    	
    	static {  // valeurs possibles
    		
    		valeurs.set(0, "FORMATEUR");
    		valeurs.set(1, "APPRENANT");
    	}
		
		public static String getTypeUtilisateur(String valeur)
		{
			if (valeur.equals("FORMATEUR")) return (String)valeurs.get(0);
			if (valeur.equals("APPRENANT")) return (String)valeurs.get(1);
			return "NULL";
		}
	}
}
