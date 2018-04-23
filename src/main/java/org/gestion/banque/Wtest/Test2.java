package org.gestion.banque.Wtest;

import java.util.List;

import org.gestion.banque.entities.Compte;
import org.gestion.banque.entities.Operation;
import org.gestion.banque.metier.IBanqueService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test2 {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext cxt = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });
		IBanqueService service = (IBanqueService) cxt.getBean("metier");
		System.out.println("--------------------------");
		Compte compte1 = service.consulterCompte("CC1");
		System.out.println("solde : " + compte1.getSolde());
		System.out.println("Date : " + compte1.getDateCreation());
		System.out.println("Nom : " + compte1.getClient().getNomClient());
		System.out.println("--------------------------");
		Compte compte2 = service.consulterCompte("CC2");
		System.out.println("solde : " + compte2.getSolde());
		System.out.println("Date : " + compte2.getDateCreation());
		System.out.println("Nom : " + compte2.getClient().getNomClient());
		System.out.println("--------------------------");
		List<Operation> operations = service.consulterOperation("CC1");
		for (Operation op : operations) {
			System.out.println("***********************");
			System.out.println("Numero Operation : " + op.getNumeroOperation());
			System.out.println(op.getDateOperation());
			System.out.println(op.getMontant());
			System.out.println("Nom de la classe de l'operation : "+op.getClass().getSimpleName());
		}
	}

}
