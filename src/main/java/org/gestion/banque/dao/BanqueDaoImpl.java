package org.gestion.banque.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.gestion.banque.entities.Client;
import org.gestion.banque.entities.Compte;
import org.gestion.banque.entities.Employe;
import org.gestion.banque.entities.Groupe;
import org.gestion.banque.entities.Operation;

public class BanqueDaoImpl implements IBanqueDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Client addClient(Client c) {
		em.persist(c);
		return c;
	}

	@Override
	public Employe addEmploye(Employe e, Long codeEmp) {
		if (codeEmp != null) {
			Employe sup = em.find(Employe.class, codeEmp);
			e.setEmployeSup(sup);
		}
		em.persist(e);
		return e;
	}

	@Override
	public Groupe addGroupe(Groupe g) {
		em.persist(g);
		return g;
	}

	@Override
	public void addEmployerToGroupe(Long codeEmp, Long codeGroupe) {
		Employe e = em.find(Employe.class, codeEmp);
		Groupe g = em.find(Groupe.class, codeGroupe);
		e.getGroupes().add(g);
		g.getEmployes().add(e);
	}

	@Override
	public Compte addCompte(Compte c, Long codeCli, Long codeEmp) {
		Client client = em.find(Client.class, codeCli);
		Employe employe = em.find(Employe.class, codeEmp);
		c.setClient(client);
		c.setEmploye(employe);
		em.persist(c);
		return c;
	}

	@Override
	public Operation addOperation(Operation op, String codeCpte, Long codeEmp) {
		Compte compte = consulterCompte(codeCpte);
		Employe employe = em.find(Employe.class, codeEmp);
		op.setCompte(compte);
		op.setEmploye(employe);
		em.persist(op);
		return op;
	}

	@Override
	public Compte consulterCompte(String codeCpte) {
		Compte cp = em.find(Compte.class, codeCpte);
		if (cp == null)
			throw new RuntimeException("Le compte rechercher n'existe pas");
		return cp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Operation> consulterOperation(String codeCpte) {
		Query query = em.createQuery("select o from Operation o where o.compte.codeCompte=:x");
		query.setParameter("x", codeCpte);
		return query.getResultList();
	}

	@Override
	public Client consulterClient(Long codeCli) {
		Client client = em.find(Client.class, codeCli);
		if (client == null)
			throw new RuntimeException("Client introuvable");
		return client;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> consulterClients(String mc) {
		Query query = em.createQuery("select c from Client c where c.nomClient like:x");
		query.setParameter("x", "%" + mc + "%");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Compte> getComptesByClient(Long codeCli) {
		Query query = em.createQuery("select c from Compte c where c.client.codeClient=:x");
		query.setParameter("x", codeCli);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Compte> getComptesByEmplye(Long codeEmp) {
		Query query = em.createQuery("select c from Compte c where c.employe.codeEmploye=:x");
		query.setParameter("x", codeEmp);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employe> getEmployes() {
		Query query = em.createQuery("select e from Employe e");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Groupe> getGroupes() {
		Query query = em.createQuery("select g from Groupe g");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employe> getEmployeByGroupe(Long codeGroupe) {
		Query query = em.createQuery("select e from Employe e where e.groupes.codeGroupe=:x ");
		query.setParameter("x", codeGroupe);
		return query.getResultList();
	}

}