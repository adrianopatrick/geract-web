package com.implementsystem.geract.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.implementsystem.geract.entity.enums.TipoAtividade;

@Entity
@Table(name="atividades")
public class Atividades implements Serializable{

	private static final long serialVersionUID = 6719861144520802118L;
	
	@Id
	@Column(name="atividade_id")
	@SequenceGenerator(name="seq_atividade", sequenceName="seq_atividade", allocationSize=1)
	@GeneratedValue(generator="seq_atividade", strategy=GenerationType.SEQUENCE)
	private Integer id;
	
	@Column
	private String descricao;
		
	@Column(precision=2, scale=2)
	private Double pontuacao;
	
	@ManyToOne
	@JoinColumn(name="entrega_id", referencedColumnName="entrega_id")
	private Entregas entrega;
	
	@ManyToOne
	@JoinColumn(name="status_atividade_id", referencedColumnName="status_atividade_id")
	private StatusAtividade status;
	
	private Integer tipo;

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

	public Double getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(Double pontuacao) {
		this.pontuacao = pontuacao;
	}

	public Entregas getEntrega() {
		return entrega;
	}

	public void setEntrega(Entregas entrega) {
		this.entrega = entrega;
	}

	public StatusAtividade getStatus() {
		return status;
	}

	public void setStatus(StatusAtividade status) {
		this.status = status;
	}
	public TipoAtividade getTipo() {
		return TipoAtividade.obtemTipoAtividade(tipo);
	}
	public void setTipo(TipoAtividade tipo) {
		this.tipo = tipo.getId();
	}
	

	@Override
	public String toString() {
		return "Atividades [id=" + id + ", descricao=" + descricao
				+ ", pontuacao=" + pontuacao + ", entrega=" + entrega
				+ ", status=" + status + ", tipo=" + tipo + "]";
	}
	
}
