package org.gestion.banque.Wtest;

import java.util.Date;

import org.gestion.banque.entities.Client;
import org.gestion.banque.entities.CompteCourant;
import org.gestion.banque.entities.CompteEpargne;
import org.gestion.banque.entities.Employe;
import org.gestion.banque.entities.Groupe;
import org.gestion.banque.metier.IBanqueService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext cxt = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });
		IBanqueService service = (IBanqueService) cxt.getBean("metier");
		service.addClient(new Client("Wajih", "Korba"));
		service.addClient(new Client("mohamed", "AD12"));
		service.addClient(new Client("samir", "BN95"));
		service.addClient(new Client("salah", "AD3"));

		service.addEmploye(new Employe("S1"), null);
		service.addEmploye(new Employe("Nabil"), 1L);
		service.addEmploye(new Employe("Naiim"), 1L);
		service.addEmploye(new Employe("Halim"), 3L);

		service.addGroupe(new Groupe("GN1"));
		service.addGroupe(new Groupe("GH1"));
		service.addGroupe(new Groupe("GM1"));

		service.addEmployerToGroupe(1L, 1L);
		service.addEmployerToGroupe(2L, 1L);
		service.addEmployerToGroupe(3L, 3L);

		service.addCompte(new CompteCourant("CC1", new Date(), 1500, 8000), 1L, 2L);
		service.addCompte(new CompteCourant("CC2", new Date(), 68000, 10000), 2L, 3L);
		service.addCompte(new CompteEpargne("CE1", new Date(), 120, 8), 3L, 3L);
		
		service.verser(520, "CC1", 2L);
		service.retirer(30, "CE1", 3L);
		service.virement(310, "CE1", "CC1", 1L);
	}

}
