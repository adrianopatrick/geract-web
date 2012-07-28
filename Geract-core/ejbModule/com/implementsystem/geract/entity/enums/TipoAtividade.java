package com.implementsystem.geract.entity.enums;


public enum TipoAtividade{
	
	DESENVOLVIMENTO(1, "Desenvolvimento"),
	ANALISE(2, "An‡lise"),
	GESTAO(3, "Gest‹o");
	
	private TipoAtividade(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	private Integer id;	
	private String descricao;
	
	public static TipoAtividade obtemTipoAtividade(Integer id){
		
		for (TipoAtividade tipo : values()) {
			if(tipo.getId() == id)
				return tipo;
		}
		return null;
	}

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


}
