package it.ricette.dto;

public class CategoriaDto {
    private Integer id;
    private String categoria;

    public CategoriaDto() {
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public CategoriaDto(Integer id, String categoria) {
        this.id = id;
        this.categoria = categoria;
    }
}
