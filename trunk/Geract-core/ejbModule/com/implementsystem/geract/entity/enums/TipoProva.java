package com.implementsystem.geract.entity.enums;

public enum TipoProva {
	
	AP1(1, "AP1"),
	AP2(2, "AP2");
	
	private Integer codigo;
	private String prova;
	
	private TipoProva(Integer codigo, String prova){
		this.codigo = codigo;
		this.prova = prova;
	}
	
	public static TipoProva getTipoProva(Integer codigo){
		for (TipoProva tipo : values()) {
			if(tipo.getCodigo() == codigo)
				return tipo;
		}
		return null;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getProva() {
		return prova;
	}

	public void setProva(String prova) {
		this.prova = prova;
	}

}
