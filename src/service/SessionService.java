package service;

import java.util.Collection;

import metier.Formation;
import metier.Session;
import dao.FormationDAO;
import dao.SessionDAO;
import exception.CheckException;
import exception.DeleteException;
import exception.ObjectNotFoundException;
import exception.RetrieveException;
import exception.UpdateException;


public class SessionService extends Service {
	
	private CourService gestionCours;
	private FormationDAO formationDAO;
	private SessionDAO sessionDAO;
	
	public SessionService(FormationDAO formationDAO) 
	{
		this.formationDAO = formationDAO;
		sessionDAO = new SessionDAO();
		gestionCours = new CourService(sessionDAO);
	}
	
	public void createSessions(String id, String nom, String desc, String dateDebut, String dateFin, int nbrPlace, Formation formation)
	{
		checkId(id);
		
		Session session = new Session(id, nom, desc, dateDebut, dateFin, nbrPlace, formation);
		
		session.checkData();
		
		sessionDAO.insert(session);
		
	}
	
	public Session retrieveSession(String idSession)  throws RetrieveException, CheckException
	{

		checkId(idSession);

		// cherche l'objet a partir d'un ID
		final Session session = (Session) sessionDAO.findByPrimaryKey(idSession);

		return session;
	}
	
	
	
	public void updateSession(String id, String nom, String desc, String dateDebut, String dateFin, int nbrPlace, Formation formation)
	{
		
		checkId(id);
        
		Session session;
        // Check, verifie si l'objet existe
        try {
        	session = (Session) sessionDAO.findByPrimaryKey(id);
        } catch (RetrieveException e) {
            throw new CheckException("Product must exist to be updated");
        }

        // cherche l'objet lié formation
        Formation formation2 = null;
        try {
        	formation2 = (Formation)formationDAO.findByPrimaryKey(formation.getId());
        } catch (RetrieveException e) {
            throw new CheckException("Category must exist to update a product");
        }

        // Updates mise a jour de l'objet
        try {
        	sessionDAO.update(session);
        } catch (ObjectNotFoundException e) {
            throw new UpdateException("Product must exist to be updated");
        }
				
	}
	
	
	public void deleteSession(String idSession) throws DeleteException, CheckException
	{
		checkId(idSession);

        // Checks vérifie si l'objet existe
        try {
            sessionDAO.findByPrimaryKey(idSession);
        } catch (RetrieveException e) {
            throw new CheckException("Product must exist to be deleted");
        }

        // Delete , supprime l'objet
        try {
        	sessionDAO.delete(idSession);
        } catch (ObjectNotFoundException e) {
            throw new DeleteException("Product must exist to be deleted");
        }
	}

	
	public Collection retrieveAllSessions() throws RetrieveException 
	{
		 // retourne tous les objets
        final Collection sessions = sessionDAO.findAll();

        return sessions;
	}
	
	public Collection retrieveAllSessions(String idFormation) throws RetrieveException 
	{
		checkId(idFormation);

		// retourne tous les objets session d'une formation donnée
        final Collection sessions =  sessionDAO.findAll(idFormation);

        return sessions;
	}

	public CourService getGestionCours() {
		return gestionCours;
	}
}
