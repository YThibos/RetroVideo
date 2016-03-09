package be.vdab.entities;

import java.math.BigDecimal;

public class Film {

	private long id;
	private String titel;
	private Genre genre;
	private int voorraad;
	private int gereserveerd;
	private BigDecimal prijs;
	
	// CONSTRUCTORS
	public Film() {
		this.id = -1;
		this.titel = "onbepaald";
		this.genre = new Genre();
		this.voorraad = 0;
		this.gereserveerd = 0;
		this.prijs = new BigDecimal(0);
	}

	public Film(long id, String titel, Genre genre, int voorraad, int gereserveerd, BigDecimal prijs) {
		this.id = id;
		this.titel = titel;
		this.genre = genre;
		this.voorraad = voorraad;
		this.gereserveerd = gereserveerd;
		this.prijs = prijs;
	}

	// GETTERS & SETTERS
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	public int getVoorraad() {
		return voorraad;
	}
	public void setVoorraad(int voorraad) {
		this.voorraad = voorraad;
	}
	public int getGereserveerd() {
		return gereserveerd;
	}
	public void setGereserveerd(int gereserveerd) {
		this.gereserveerd = gereserveerd;
	}
	public BigDecimal getPrijs() {
		return prijs;
	}
	public void setPrijs(BigDecimal prijs) {
		this.prijs = prijs;
	}

	
	// DEFAULT OVERRIDE METHODS
	@Override
	public String toString() {
		return "Film [id=" + id + ", titel=" + titel + ", genre=" + genre + ", voorraad=" + voorraad + ", gereserveerd="
				+ gereserveerd + ", prijs=" + prijs + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((titel == null) ? 0 : titel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		if (id != other.id)
			return false;
		if (titel == null) {
			if (other.titel != null)
				return false;
		} else if (!titel.equals(other.titel))
			return false;
		return true;
	}
	
	
	
	
}
