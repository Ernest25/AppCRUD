package service;

import metier.Utilisateur;
import dao.UtilisateurDAO;
import exception.CheckException;
import exception.RetrieveException;

public class UtilisateurService extends Service  {
	
   private UtilisateurDAO utilisateurDAO;
	
	
	public UtilisateurService()
	{
		utilisateurDAO = new UtilisateurDAO();
	}
	
	public void createUtilisateur(final String id,String login, String motDePasse,String nom, 
			String prenom, String mail, String adresse, String codePostal,
			String ville, String telephoneFixe, String telephonePortable,
			String dateInscription, String derniereConnexion, int statut)
	{
		
		
	}
	
	public Utilisateur ReadUtilisateur(int idUtilisateur)
	{
		return null;
	}
	
	public void updateUtilisateur(int idUtilisateur,String login, String motDePasse,String nom, 
			String prenom, String mail, String adresse, String codePostal,
			String ville, String telephoneFixe, String telephonePortable,
			String dateInscription, String derniereConnexion, int statut)
	{
		
	}
		
	public void deleteUtilisateur(int idUtilisateur)
	{

	}
	


	
	 public Utilisateur authenticate(final String login, final String password) throws RetrieveException, CheckException {

	        checkId(login);
	        
	        if (password == null || "".equals(password))
	            throw new CheckException("Invalid password");
	        
	        // cherche l'objet
	        final Utilisateur utilisateur = (Utilisateur)utilisateurDAO.findByPrimaryKey(login);

	        // Check verifie si le mot de passe est correct, ni non , une CheckException est déclenchée
	        utilisateur.matchPassword(password);

	        
	        return utilisateur;
	    }
}
