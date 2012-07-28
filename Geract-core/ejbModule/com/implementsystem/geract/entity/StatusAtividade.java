package com.implementsystem.geract.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="status_atividade")
public class StatusAtividade implements Serializable{

	private static final long serialVersionUID = 1486693893947738193L;
	
	@Id
	@SequenceGenerator(name="seq_status_atividade", sequenceName="seq_status_atividade", initialValue=1)
	@GeneratedValue(generator="seq_status_atividade", strategy=GenerationType.SEQUENCE)
	@Column(name="status_atividade_id")
	private Integer id;
	
	@Column
	private String descricao;

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

	@Override
	public String toString() {
		return "StatusAtividade [id=" + id + ", descricao=" + descricao + "]";
	}

}
