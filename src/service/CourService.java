package service;


import dao.SessionDAO;

import metier.Cour;


/**
 * Cette classe est une classe de gestion des cours.
 * 
 * Elle fournit l'ensemble des services nécessaires pour gerer les cours à la couche Boundary (présentation)
 * 
 * @author 
 *
 */
public class CourService extends Service {

	private SessionDAO sessionDAO;
	
	private UtilisateurService gestionUtilisateurs;
	
	
	
	public CourService(SessionDAO sessionDAO) 
	{
		this.sessionDAO = sessionDAO;
		gestionUtilisateurs = new UtilisateurService();
	}

	public void createCours(final String id ,int idSession, String date, String heureDebut, String heureFin, String adresse, String salle)
	{
		
	}

	public Cour readCour(int idCours)
	{

        return null;
	}

	public void updateCours(int idCours ,int idSession, String date, String heureDebut,
			String heureFin, String adresse, String salle)
	{


	}

	public void deleteCours(int idCour)
	{
		
	}
	
	
}