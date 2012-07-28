package com.implementsystem.geract.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="entregas")
public class Entregas implements Serializable{

	private static final long serialVersionUID = 5872120579488726676L;
	
	@Id
	@Column(name="entrega_id")	
	@SequenceGenerator(name="seq_entregas", sequenceName="seq_entregas", allocationSize=1)
	@GeneratedValue(generator="seq_entregas", strategy=GenerationType.SEQUENCE)
	private Integer id;
	
	@Column
	private String descricao;
	
	@Column(name="data_marcada")
	@Temporal(TemporalType.DATE)
	private Date dataMarcada;
	
	@Column(name="data_realizada")
	@Temporal(TemporalType.DATE)
	private Date dataRealizada;
	
	@Column(precision=2, scale=2)
	private Double nota;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="equipe_id", referencedColumnName="equipe_id")
	private Equipes equipe;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="status_entrega_id", referencedColumnName="status_entrega_id")
	private StatusEntrega status;
	
	@OneToMany(mappedBy="entrega", fetch=FetchType.LAZY, orphanRemoval=true)	
	private List<Atividades> atividades = new ArrayList<Atividades>();
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataMarcada() {
		return dataMarcada;
	}

	public void setDataMarcada(Date dataMarcada) {
		this.dataMarcada = dataMarcada;
	}

	public Date getDataRealizada() {
		return dataRealizada;
	}

	public void setDataRealizada(Date dataRealizada) {
		this.dataRealizada = dataRealizada;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public Equipes getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipes equipe) {
		this.equipe = equipe;
	}

	public StatusEntrega getStatus() {
		return status;
	}

	public void setStatus(StatusEntrega status) {
		this.status = status;
	}
	public List<Atividades> getAtividades() {
		return atividades;
	}
	public void setAtividades(List<Atividades> atividades) {
		this.atividades = atividades;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((atividades == null) ? 0 : atividades.hashCode());
		result = prime * result
				+ ((dataMarcada == null) ? 0 : dataMarcada.hashCode());
		result = prime * result
				+ ((dataRealizada == null) ? 0 : dataRealizada.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((equipe == null) ? 0 : equipe.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nota == null) ? 0 : nota.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Entregas other = (Entregas) obj;
		if (atividades == null) {
			if (other.atividades != null)
				return false;
		} else if (!atividades.equals(other.atividades))
			return false;
		if (dataMarcada == null) {
			if (other.dataMarcada != null)
				return false;
		} else if (!dataMarcada.equals(other.dataMarcada))
			return false;
		if (dataRealizada == null) {
			if (other.dataRealizada != null)
				return false;
		} else if (!dataRealizada.equals(other.dataRealizada))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
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
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Entregas [id=" + id + ", descricao=" + descricao
				+ ", dataMarcada=" + dataMarcada + ", dataRealizada="
				+ dataRealizada + ", nota=" + nota + ", equipe=" + equipe
				+ ", status=" + status + ", atividades=" + atividades + "]";
	}

}
