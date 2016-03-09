package be.vdab.entities;

import java.math.BigDecimal;

/**
 * Builder for the class Film.
 * 
 * @author yannick.thibos
 *
 */
public class FilmBuilder {
	
	private long id;
	private String titel;
	private Genre genre;
	private int voorraad;
	private int gereserveerd;
	private BigDecimal prijs;
	
	public FilmBuilder() {
	
	}

	public FilmBuilder setId(long id) {
		this.id = id;
		return this;
	}
	
	public FilmBuilder setTitel(String titel) {
		this.titel = titel;
		return this;
	}
	
	public FilmBuilder setGenre(Genre genre) {
		this.genre = genre;
		return this;
	}
	
	public FilmBuilder setVoorraad(int voorraad) {
		this.voorraad = voorraad;
		return this;
	}
	
	public FilmBuilder setGereserveerd(int gereserveerd) {
		this.gereserveerd = gereserveerd;
		return this;
	}
	
	public FilmBuilder setPrijs(BigDecimal prijs) {
		this.prijs = prijs;
		return this;
	}
	
	public Film createFilm() {
		return new Film(id, titel, genre, voorraad, gereserveerd, prijs);
	}
	
}
