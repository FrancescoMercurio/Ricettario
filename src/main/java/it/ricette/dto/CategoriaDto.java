package it.ricette.dto;

public class CategoriaDto {
    private Integer id;
    private String nomeCategoria;

    public CategoriaDto() {
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public CategoriaDto(Integer id, String nomeCategoria) {
        this.id = id;
        this.nomeCategoria = nomeCategoria;
    }
}
