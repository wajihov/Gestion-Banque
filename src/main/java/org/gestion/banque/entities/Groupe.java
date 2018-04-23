package org.gestion.banque.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
@SuppressWarnings("serial")
public class Groupe implements Serializable {

	@Id
	@GeneratedValue
	private Long codeGroupe;
	@Column(name = "NOMGROUPE", length = 80)
	private String nomGroupe;
	// 1 groupe contient plusieur employe
	@ManyToMany(mappedBy = "groupes")
	private Collection<Employe> employes;

	public Long getCodeGroupe() {
		return codeGroupe;
	}

	public void setCodeGroupe(Long codeGroupe) {
		this.codeGroupe = codeGroupe;
	}

	public String getNomGroupe() {
		return nomGroupe;
	}

	public void setNomGroupe(String nomGroupe) {
		this.nomGroupe = nomGroupe;
	}

	public Collection<Employe> getEmployes() {
		return employes;
	}

	public void setEmployes(Collection<Employe> employes) {
		this.employes = employes;
	}

	public Groupe() {
		super();
	}

	public Groupe(String nomGroupe) {
		super();
		this.nomGroupe = nomGroupe;
	}

}