package org.gestion.banque.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@SuppressWarnings("serial")
public class Employe implements Serializable {

	@Id
	@GeneratedValue
	private Long codeEmploye;
	@Column(name = "NOMEMPLOYE", length = 80)
	private String nomEmploye;
	@ManyToOne
	@JoinColumn(name = "CODE_EMP_SUP")
	private Employe employeSup;
	// 1 employe appartient a plusieur groupe
	@ManyToMany
	@JoinTable(name = "EMP_GR", joinColumns = @JoinColumn(name = "CODE_EMP"), inverseJoinColumns = @JoinColumn(name = "CODE_GR"))
	private Collection<Groupe> groupes;

	public Long getCodeEmploye() {
		return codeEmploye;
	}

	public void setCodeEmploye(Long codeEmploye) {
		this.codeEmploye = codeEmploye;
	}

	public String getNomEmploye() {
		return nomEmploye;
	}

	public void setNomEmploye(String nomEmploye) {
		this.nomEmploye = nomEmploye;
	}

	public Employe getEmployeSup() {
		return employeSup;
	}

	public void setEmployeSup(Employe employeSup) {
		this.employeSup = employeSup;
	}

	public Collection<Groupe> getGroupes() {
		return groupes;
	}

	public void setGroupes(Collection<Groupe> groupes) {
		this.groupes = groupes;
	}

	public Employe() {
		super();
	}

	public Employe(String nomEmploye) {
		super();
		this.nomEmploye = nomEmploye;
	}

	public Employe(String nomEmploye, Employe employeSup) {
		super();
		this.nomEmploye = nomEmploye;
		this.employeSup = employeSup;
	}

}