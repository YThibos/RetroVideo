package be.vdab.entities;

import java.math.BigDecimal;

/**
 * Builder voor de Film class. Alle velden zijn verplicht in te vullen.
 * Zoniet zal createFilm een exception throwen.
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
		this.id = -1;
		this.titel = null;
		this.genre = null;
		this.voorraad = -1;
		this.gereserveerd = -1;
		this.prijs = null;
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
	
	/**
	 * Creëer een nieuwe film met de attributen ingevuld door de setters v/d Builder.
	 * Alle velden moeten eerst gezet worden voordat createFilm succesvol een Film returnt.
	 * 
	 * @return Een Film object met attributen gezet door de setters van de FilmBuilder.
	 * @throws FilmException Wanneer minstens één setter werd overgeslagen.
	 */
	public Film createFilm() throws FilmException {
		if (id != -1 && titel != null && genre != null && voorraad != -1 && gereserveerd != -1 && prijs != null) {
			return new Film(id, titel, genre, voorraad, gereserveerd, prijs);
		}
		else {
			throw new FilmException("Een van de velden werd niet ingevuld in de FilmBuilder");
		}
	}
	
}
