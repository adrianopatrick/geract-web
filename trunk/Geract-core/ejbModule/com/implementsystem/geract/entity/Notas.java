package com.implementsystem.geract.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.implementsystem.geract.entity.enums.TipoProva;

@Entity
@Table(name="notas")
public class Notas implements Serializable{

	private static final long serialVersionUID = -4434775316601184703L;
	
	@Id
	@Column(name="nota_id")
	@SequenceGenerator(name="seq_notas", sequenceName="seq_notas", allocationSize=1)
	@GeneratedValue(generator="seq_notas", strategy=GenerationType.SEQUENCE)
	private Integer id;
	
	@JoinColumn(name="aluno_id")
	@ManyToOne(fetch=FetchType.EAGER)
	private Alunos aluno;
	
	@JoinColumn(name="equipe_id")
	@ManyToOne(fetch=FetchType.EAGER)
	private Equipes equipe;
	
	@JoinColumn(name="entrega_id")
	@ManyToOne(fetch=FetchType.EAGER)
	private Entregas entrega;
	
	@Column(precision=2, scale=2)
	private Double nota;
	
	private Integer prova;
	
	@Column(name="data_cadastro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@Column(name="data_alteracao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAlteracao;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Alunos getAluno() {
		return aluno;
	}
	public void setAluno(Alunos aluno) {
		this.aluno = aluno;
	}
	public Equipes getEquipe() {
		return equipe;
	}
	public void setEquipe(Equipes equipe) {
		this.equipe = equipe;
	}
	public Entregas getEntrega() {
		return entrega;
	}
	public void setEntrega(Entregas entrega) {
		this.entrega = entrega;
	}
	public Double getNota() {
		return nota;
	}
	public void setNota(Double nota) {
		this.nota = nota;
	}
	public TipoProva getProva() {
		return TipoProva.getTipoProva(prova);
	}
	public void setProva(TipoProva prova) {
		this.prova = prova.getCodigo();
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Date getDataAlteracao() {
		return dataAlteracao;
	}
	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
		result = prime * result
				+ ((dataAlteracao == null) ? 0 : dataAlteracao.hashCode());
		result = prime * result
				+ ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result + ((entrega == null) ? 0 : entrega.hashCode());
		result = prime * result + ((equipe == null) ? 0 : equipe.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nota == null) ? 0 : nota.hashCode());
		result = prime * result + ((prova == null) ? 0 : prova.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notas other = (Notas) obj;
		if (aluno == null) {
			if (other.aluno != null)
				return false;
		} else if (!aluno.equals(other.aluno))
			return false;
		if (dataAlteracao == null) {
			if (other.dataAlteracao != null)
				return false;
		} else if (!dataAlteracao.equals(other.dataAlteracao))
			return false;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		if (entrega == null) {
			if (other.entrega != null)
				return false;
		} else if (!entrega.equals(other.entrega))
			return false;
		if (equipe == null) {
			if (other.equipe != null)
				return false;
		} else if (!equipe.equals(other.equipe))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nota == null) {
			if (other.nota != null)
				return false;
		} else if (!nota.equals(other.nota))
			return false;
		if (prova != other.prova)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Notas [id=" + id + ", aluno=" + aluno + ", equipe=" + equipe
				+ ", entrega=" + entrega + ", nota=" + nota + ", prova="
				+ prova + ", dataCadastro=" + dataCadastro + ", dataAlteracao="
				+ dataAlteracao + "]";
	}

}
