package service;

import java.util.Collection;

import metier.Categorie;
import dao.CategorieDAO;
import exception.CheckException;
import exception.CreateException;
import exception.DeleteException;
import exception.ObjectNotFoundException;
import exception.RetrieveException;
import exception.UpdateException;

public class CategorieService extends Service {

	// ======================================
    // =             Attributs             =
    // ======================================
	
	
	private CategorieDAO categorieDAO;
	
	private FormationService gestionFormation;
	
	
	// ======================================
    // =            Constructeurs           =
    // ======================================
	
	public CategorieService() 
	{
		categorieDAO = new CategorieDAO();
		
		gestionFormation = new FormationService(categorieDAO);
	}
	
	
	
	// =============================================
    // =      Categorie : methodes métier CRUD     =
    // =============================================
	
	public void createCategorie(final String idCategorie, final String nom, final String description)   throws CreateException, CheckException 
	{
		checkId(idCategorie);
		
		final Categorie categorie = new Categorie(idCategorie, nom, description);
		
		categorie.checkData();
		
		categorieDAO.insert(categorie);
	}
	
	
	 
	 
	
	public Categorie retrieveCategorie(final String idCategorie) throws RetrieveException, CheckException 
	{
		if (idCategorie == null || "".equals(idCategorie))
            throw new CheckException("Invalid id");
		
		final Categorie categorie = (Categorie) categorieDAO.findByPrimaryKey(idCategorie);
		
		return categorie;
	}
	
	
	
	
	public void updateCategorie(final String idCategorie, final String nom, final String description)  throws UpdateException, CheckException 
	{
		// Check, verifie si l'Id est correct
		checkId(idCategorie);
		
        Categorie categorie = new Categorie();

     // Verifie si l'objet existe
        try {
            categorie = (Categorie) categorieDAO.findByPrimaryKey(idCategorie);
        } catch (RetrieveException e) {
            throw new CheckException("Category must exist to be updated");
        }

        // renseigne les champs de la catégorie
        categorie.setNom(nom);
        categorie.setDescription(description);

        // Update , met à jour l'objet catégorie
        try {
        	categorieDAO.update(categorie);
        } catch (ObjectNotFoundException e) {
            throw new UpdateException("Category must exist to be updated");
        }
	}

	
	
	
	
	public void deleteCategorie(final String idCategorie) throws DeleteException, CheckException 
	{
		
		  checkId(idCategorie);

	        // Checks, verfifie  si l'object existe
	        try {
	            categorieDAO.findByPrimaryKey(idCategorie);
	        } catch (RetrieveException e) {
	            throw new CheckException("Category must exist to be deleted");
	        }

	        // Delete , supprime  l'objet
	        try {
	        	categorieDAO.delete(idCategorie);
	        } catch (ObjectNotFoundException e) {
	            throw new DeleteException("Category must exist to be deleted");
	        }
	}
	
	
	public Collection retrieveAllCategories() throws RetrieveException
	{
        // Rerche tous les objets categories
        final Collection categories = categorieDAO.findAll();

        return categories;
	}



	public FormationService getGestionFormation() {
		return gestionFormation;
	}
}
