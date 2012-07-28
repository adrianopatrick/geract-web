package com.implementsystem.geract.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="equipes")
public class Equipes implements Serializable {

	private static final long serialVersionUID = 4481609931211569552L;
	
	@Id
	@Column(name="equipe_id")
	@SequenceGenerator(name="seq_equipe", sequenceName="seq_equipe", allocationSize=1)
	@GeneratedValue(generator="seq_equipe", strategy=GenerationType.SEQUENCE)
	private Integer id;
	
	@Column
	private String nome;
	
	@OneToMany(mappedBy="equipe", fetch=FetchType.EAGER)
	private List<Alunos> alunos = new ArrayList<Alunos>();
	
	@OneToMany(mappedBy="equipe", fetch=FetchType.LAZY)
	private List<Entregas> entregas = new ArrayList<Entregas>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Alunos> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Alunos> alunos) {
		this.alunos = alunos;
	}

	public List<Entregas> getEntregas() {
		return entregas;
	}

	public void setEntregas(List<Entregas> entregas) {
		this.entregas = entregas;
	}

	@Override
	public String toString() {
		return "Equipes [id=" + id + ", nome=" + nome + ", alunos=" + alunos
				+ ", entregas=" + entregas + "]";
	}

}
