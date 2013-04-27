package service;

import java.util.HashMap;

import metier.Document;
import metier.Utilisateur;

public class DocumentService extends Service {
	
	private UtilisateurService gestionUtilisateurs;
	
	public DocumentService() 
	{
		gestionUtilisateurs = new UtilisateurService();
	}
	
	public void createDocument(final String id,int idUtilisateurDeposant, String titre, String format,
			String dateCreation, String dateModifcation, String url,
			String description, int nbTelechargement)
	{
	}
	
	public Document retrieveDocument(String idDocument)
	{
		return null;
	}
	
	public void updateDocument(String  idDocument,int idUtilisateurDeposant, String titre, String format,
			String dateCreation, String dateModifcation, String url,
			String description, int nbTelechargement)
	{


	}
	
	public void deleteDocument(String idDocument)
	{
	}
	
	public void addUtilisateurTelechargeants(String  idDocument, int idUtilisateur)//les users deposant se font direct via le constructeur de documement
	{


	}
}
