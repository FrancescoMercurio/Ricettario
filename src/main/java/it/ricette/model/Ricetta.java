package it.ricette.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Ricetta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "titolo")
	private String titolo;
	
	@Column(name = "quantitaPersone")
	private Integer quantitaPersone;
	
	@Column(name = "preparazione")
	private String preparazione;
	
	@Column(name = "ingredienti")
	private String ingredienti;
	
	@ManyToOne
	@JoinColumn(name = "id_categorie")
	private Categoria categoria;
	
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
	public Integer getQuantitaPersone() {
		return quantitaPersone;
	}
	public void setQuantitaPersone(Integer quantitaPersone) {
		this.quantitaPersone = quantitaPersone;
	}
	public String getPreparazione() {
		return preparazione;
	}
	public void setPreparazione(String preparazione) {
		this.preparazione = preparazione;
	}
	public String getIngredienti() {
		return ingredienti;
	}
	public void setIngredienti(String ingredienti) {
		this.ingredienti = ingredienti;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public Ricetta(Integer id, String titolo, Integer quantitaPersone, String preparazione, String ingredienti, Categoria categoria) {
		super();
		this.id = id;
		this.titolo = titolo;
		this.quantitaPersone = quantitaPersone;
		this.preparazione = preparazione;
		this.ingredienti = ingredienti;
		this.categoria = categoria;
	}

	public Ricetta() {
		super();
	}
}