package be.vdab.entities;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Class die een Film en zijn eigenschappen beschrijft, conform de eigenschappen zoals in de RetroVideo database.
 * 
 * @author Yannick.Thibos
 *
 */
public class Film {
	
	private long id;
	private String titel;
	private Genre genre;
	private int voorraad;
	private int gereserveerd;
	private BigDecimal prijs;
	
	// CONSTRUCTORS
	/**
	 * Default constructor met waarden:
	 * <br> id = 0 <br> titel = "onbepaald"
	 * <br> genre See {@link Genre() Genre default constructor}
	 * <br> voorraad = 0 <br> gereserveerd = 0
	 * <br> prijs = BigDecimal.ZERO
	 */
	public Film() {
		this.id = 0;
		this.titel = "onbepaald";
		this.genre = new Genre();
		this.voorraad = 0;
		this.gereserveerd = 0;
		this.prijs = BigDecimal.ZERO;
	}

	public Film(long id, String titel, Genre genre, int voorraad, int gereserveerd, BigDecimal prijs) throws FilmException {
		setId(id);
		setTitel(titel);
		setGenre(genre);
		setVoorraad(voorraad);
		setGereserveerd(gereserveerd);
		setPrijs(prijs);
	}

	// GETTERS & SETTERS
	public long getId() {
		return id;
	}
	public void setId(long id) throws FilmException {
		if (id < 0) {
			throw new FilmException("Een film ID mag niet negatief zijn");
		}
		this.id = id;
	}
	public String getTitel() {
		return titel;
	}
	public final void setTitel(String titel) throws FilmException {
		Objects.requireNonNull(titel, "Een film titel mag niet null zijn");
		if (titel.equals("")) {
			throw new FilmException("Een film mag geen lege titel hebben");
		}
		this.titel = titel;
	}
	public Genre getGenre() {
		return genre;
	}
	public final void setGenre(Genre genre) {
		Objects.requireNonNull(genre, "Een film genre mag niet null zijn");
		this.genre = genre;
	}
	public int getVoorraad() {
		return voorraad;
	}
	public final void setVoorraad(int voorraad) throws FilmException {
		if (isNegative(voorraad)) {
			throw new FilmException("Een film voorraad kan niet negatief zijn");
		}
		this.voorraad = voorraad;
	}
	public int getGereserveerd() {
		return gereserveerd;
	}
	public final void setGereserveerd(int gereserveerd) throws FilmException {
		if (isNegative(gereserveerd)) {
			throw new FilmException("Aantal reservaties v/e film kan niet negatief zijn");
		}
		this.gereserveerd = gereserveerd;
	}
	public BigDecimal getPrijs() {
		return prijs;
	}
	public final void setPrijs(BigDecimal prijs) throws FilmException {
		Objects.requireNonNull(prijs, "De prijs van een film mag niet null zijn");
		if (prijs.compareTo(BigDecimal.ZERO) < 0) {
			throw new FilmException("De prijs van een film mag niet negatief zijn");
		}
		this.prijs = prijs;
	}

	private final boolean isNegative(long number) {
		return number < 0;
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
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((titel == null) ? 0 : titel.hashCode());
		return result;
	}

	/**
	 * Een film is 'equal' aan een andere film als hij dezelfde id, titel en genre heeft.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
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
