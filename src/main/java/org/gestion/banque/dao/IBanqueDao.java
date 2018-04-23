package org.gestion.banque.dao;

import java.util.List;

import org.gestion.banque.entities.Client;
import org.gestion.banque.entities.Compte;
import org.gestion.banque.entities.Employe;
import org.gestion.banque.entities.Groupe;
import org.gestion.banque.entities.Operation;

public interface IBanqueDao {

	public Client addClient(Client c);

	public Employe addEmploye(Employe e, Long codeEmp);

	public Groupe addGroupe(Groupe g);

	public void addEmployerToGroupe(Long codeEmp, Long codeGroupe);

	public Compte addCompte(Compte c, Long codeCli, Long codeEmp);

	public Operation addOperation(Operation op, String codeCpte, Long codeEmp);

	public Compte consulterCompte(String codeCpte);

	public List<Operation> consulterOperation(String codeCpte);

	public Client consulterClient(Long codeCli);

	public List<Client> consulterClients(String mc);

	public List<Compte> getComptesByClient(Long codeCli);

	public List<Compte> getComptesByEmplye(Long codeEmp);

	public List<Employe> getEmployes();

	public List<Groupe> getGroupes();

	public List<Employe> getEmployeByGroupe(Long codeGroupe);
}