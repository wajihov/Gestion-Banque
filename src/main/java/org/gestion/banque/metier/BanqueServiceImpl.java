package org.gestion.banque.metier;

import java.util.Date;
import java.util.List;

import org.gestion.banque.dao.IBanqueDao;
import org.gestion.banque.entities.Client;
import org.gestion.banque.entities.Compte;
import org.gestion.banque.entities.Employe;
import org.gestion.banque.entities.Groupe;
import org.gestion.banque.entities.Operation;
import org.gestion.banque.entities.Retrait;
import org.gestion.banque.entities.Versement;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class BanqueServiceImpl implements IBanqueService {

	private IBanqueDao dao;

	public void setDao(IBanqueDao dao) {
		this.dao = dao;
	}

	@Override
	public Client addClient(Client c) {
		return dao.addClient(c);
	}

	@Override
	public Employe addEmploye(Employe e, Long codeEmp) {
		return dao.addEmploye(e, codeEmp);
	}

	@Override
	public Groupe addGroupe(Groupe g) {
		return dao.addGroupe(g);
	}

	@Override
	public void addEmployerToGroupe(Long codeEmp, Long codeGroupe) {
		dao.addEmployerToGroupe(codeEmp, codeGroupe);
	}

	@Override
	public Compte addCompte(Compte c, Long codeCli, Long codeEmp) {
		return dao.addCompte(c, codeCli, codeEmp);
	}

	@Override
	public void verser(double mt, String cpte, Long codeEmp) {
		dao.addOperation(new Versement(new Date(), mt), cpte, codeEmp);
		Compte cpt = dao.consulterCompte(cpte);
		cpt.setSolde(cpt.getSolde() + mt);
	}

	@Override
	public void retirer(double mt, String cpte, Long codeEmp) {
		dao.addOperation(new Retrait(new Date(), mt), cpte, codeEmp);
		Compte cpt = dao.consulterCompte(cpte);
		cpt.setSolde(cpt.getSolde() - mt);
	}

	@Override
	public void virement(double mt, String cpte1, String cpte2, Long codeEmp) {
		retirer(mt, cpte1, codeEmp);
		verser(mt, cpte2, codeEmp);
	}

	@Override
	public Compte consulterCompte(String codeCpte) {
		return dao.consulterCompte(codeCpte);
	}

	@Override
	public List<Operation> consulterOperation(String codeCpte) {
		return dao.consulterOperation(codeCpte);
	}

	@Override
	public Client consulterClient(Long codeCli) {
		return dao.consulterClient(codeCli);
	}

	@Override
	public List<Client> consulterClients(String mc) {
		return dao.consulterClients(mc);
	}

	@Override
	public List<Compte> getComptesByClient(Long codeCli) {
		return dao.getComptesByClient(codeCli);
	}

	@Override
	public List<Compte> getComptesByEmplye(Long codeEmp) {
		return dao.getComptesByEmplye(codeEmp);
	}

	@Override
	public List<Employe> getEmployes() {
		return dao.getEmployes();
	}

	@Override
	public List<Groupe> getGroupes() {
		return dao.getGroupes();
	}

	@Override
	public List<Employe> getEmployeByGroupe(Long codeGroupe) {
		return dao.getEmployeByGroupe(codeGroupe);
	}

}