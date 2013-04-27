package service;

import java.util.Collection;

import metier.Categorie;
import metier.Formation;
import metier.Session;
import dao.CategorieDAO;
import dao.FormationDAO;
import exception.CheckException;
import exception.DeleteException;
import exception.ObjectNotFoundException;
import exception.RetrieveException;
import exception.UpdateException;

public class FormationService extends Service   {
	
	
	// ======================================
    // =             Attributs             =
    // ======================================
	
	private FormationDAO formationDAO;
	private SessionService gestionSessions;
	
	private CategorieDAO categorieDao;
	
	
	
	// ======================================
    // =            Constructeurs           =
    // ======================================
	
	public FormationService(CategorieDAO categorieDao) 
	{	
		formationDAO = new FormationDAO();
		gestionSessions = new SessionService(formationDAO);
		
		this.categorieDao=categorieDao;
	}
	
	
	
	// =============================================
    // =      Formation : methodes métier CRUD     =
    // =============================================
	
	
	public void createFormation(String id, String libelle, String preRequis, String description, double prix, Categorie categorie)
	{
		checkId(id);
		
		Formation formation = new Formation(id, libelle, preRequis, description, prix, categorie);
		
		formation.checkData();
		
		formationDAO.insert(formation);
	}
	
	
	
	public Formation retrieveFormation(final String  idFormation)
	{
		checkId(idFormation);

        // cherche une formation avec idFormation
        final Formation formation = (Formation) formationDAO.findByPrimaryKey(idFormation);
        
		return formation;
	}

	
	
	
	public void updateFormation(String idFormation, String libelle, String preRequis, String description, double prix, String categorieId)
	{
		// Verfier l'ID
		checkId(idFormation);
		
		// cherche une formation avec idFormation
        final Formation formation = (Formation) formationDAO.findByPrimaryKey(idFormation);
        
     // mettre a jour la formation
        formation.setLibelle(libelle);
        formation.setPreRequis(preRequis);
        formation.setDescription(description);
        formation.setPrix(prix);
        
        
       // cherche l'objet lié Categorie
        Categorie categorie = null;
        try {
        	categorie = (Categorie) categorieDao.findByPrimaryKey(categorieId);
        } catch (RetrieveException e) {
            throw new CheckException("Category must exist to update a formation");
        }
        
        // Updates, met a jour l'objet
        try {
        	formationDAO.update(formation);
        } catch (ObjectNotFoundException e) {
            throw new UpdateException("Formation must exist to be updated");
        }
	}

	
	
	public void deleteFormation(final String idFormation)  throws DeleteException, CheckException
	{

		checkId(idFormation);

        // Check, verifie si l'objet existe
        try {
            formationDAO.findByPrimaryKey(idFormation);
        } catch (RetrieveException e) {
            throw new CheckException("Formation must exist to be deleted");
        }

        // Delete, supprime l'objet
        try {
        	formationDAO.delete(idFormation);
        } catch (ObjectNotFoundException e) {
            throw new DeleteException("Formation must exist to be deleted");
        }
	}
	
	
	public Collection retrieveAllFormations() throws RetrieveException
	{
        // Rerche tous les objets 
        final Collection formations = formationDAO.findAll();

        return formations;
	}



	public SessionService getGestionSessions() {
		return gestionSessions;
	}
	
}
