package vue;


import java.util.Scanner;

import service.CategorieService;
import service.CourService;
import service.FormationService;
import service.SessionService;
import service.UtilisateurService;


public class Console  {
	
	
	private CategorieService categorieService;
	private FormationService formationService;
	private SessionService sessionService;
	private CourService courService;
	private UtilisateurService utilisateurService;
	
	
	
	
	public Console()
	{
		categorieService = new CategorieService();
		formationService = categorieService.getGestionFormation();
		sessionService = formationService.getGestionSessions();
		courService = sessionService.getGestionCours();	
	}

	public void afficherMenu()
	{
		
		System.out.println("1- Demander congés");
		System.out.println("2- Valider congés");
		System.out.println("3- Consulter planning");
		System.out.println("5- Exit");
		System.out.println(" \n \t Veuillez saisir votre choix :");
	}
	

	public  void menu()
	{
		Scanner sc = new Scanner(System.in);
		
		while (true) {
		
		   afficherMenu();

		   int choix = sc.nextInt();

		   switch(choix)
		   {
		       case 1: {     }  break;

		       case 2: {     }  break;   

		       case 3: {     }  break;
		       
		       case 5: {  sc.close(); System.exit(0);  }   break;
		
		       default: {  System.out.println("Veullez saisir un choix valide ");   }
		  }
		
		}
	}
	
	public void menuLogin()
	{
		Scanner sc = new Scanner(System.in);
		
		afficherMenuLogin();

		   int choix = sc.nextInt();

		   switch(choix)
		   {
		       case 0: {  authentifier(sc);   }  break;

		       case 4: {  enregistrer(sc);    }   break;
		       
		       case 5: {  sc.close(); System.exit(0);  }   break;
		
		       default: {  System.out.println("Veullez saisir un choix valide ");   }
		  }
	}
	
	


	private void afficherMenuLogin()
	{
		System.out.println("0- Authentifier Vous");
		System.out.println("4- Enregistrer vous en tant que employé");
		System.out.println("5- Exit");
		
	}

	public void enregistrer(Scanner sc)
	{
		try{
			System.out.println("ENREGISTREMENT : ");
			System.out.println("Veuillez préciser les informations suivantes.");
			System.out.println("Nom :");
			String nom = sc.next();
			System.out.println("Login :");
			String login = sc.next();
			System.out.println("Password :");
			String password = sc.next();
			// utilisateurService.createUtilisateur(nom, "EMPLOYE", login, password);
			
			menuLogin();

		}
		catch(Exception e)
		{
			System.out.println("Exception dans console.enregistrer()");
		}
	}


	public void authentifier(Scanner sc)
	{
		try{
			System.out.println("AUTHENTIFICATION : ");
			System.out.println("Votre login:");
			String login = sc.next();
			System.out.println("Votre mot de passe:");
			String mdp = sc.next();

			if(utilisateurService.authenticate(login, mdp)!=null)
			{
				System.out.println("Bravo ! Vous êtes authentifié par le système ! ");
				
				menu();
			} else {
				System.out.println("Le login et mot de passe n'existent pas dans la base ! ");
			}
		}
		catch(Exception e)
		{
			System.out.println("Erreur dans Console.authentifier()");
		}

	}




	
	
	
	
	public static void main(String[] args)
	{
		Console presentation = new Console();
		presentation.menuLogin();

	}
}
