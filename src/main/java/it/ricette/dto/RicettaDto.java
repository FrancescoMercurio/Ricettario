package it.ricette.dto;

public class RicettaDto {
    private Integer id;
    private String titolo;
    private String preparazione;
    private Integer quantitaPersone;
    private String ingredienti;
    private CategoriaDto categoria;
//    private Set<User> usersWhoFavorited = new HashSet<>();

    public RicettaDto() {
    }

    public RicettaDto(Integer id, String titolo, String preparazione, Integer quantitaPersone, String ingredienti,
			CategoriaDto categoria/*, Set<User> usersWhoFavorited*/) {
		super();
		this.id = id;
		this.titolo = titolo;
		this.preparazione = preparazione;
		this.quantitaPersone = quantitaPersone;
		this.ingredienti = ingredienti;
		this.categoria = categoria;
//		this.usersWhoFavorited = usersWhoFavorited;
	}



//	public Set<User> getUsersWhoFavorited() {
//		return usersWhoFavorited;
//	}
//
//	public void setUsersWhoFavorited(Set<User> usersWhoFavorited) {
//		this.usersWhoFavorited = usersWhoFavorited;
//	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getPreparazione() {
        return preparazione;
    }

    public void setPreparazione(String preparazione) {
        this.preparazione = preparazione;
    }

    public Integer getQuantitaPersone() {
        return quantitaPersone;
    }

    public void setQuantitaPersone(Integer quantitaPersone) {
        this.quantitaPersone = quantitaPersone;
    }

    public String getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(String ingredienti) {
        this.ingredienti = ingredienti;
    }

    public CategoriaDto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDto categoria) {
        this.categoria = categoria;
    }
}
