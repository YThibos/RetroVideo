package be.vdab.entities;

/**
 * Class die een Genre en zijn eigenschappen beschrijft, conform de eigenschappen zoals in de RetroVideo database.
 * @author Thibos
 *
 */
public class Genre {
	
	private long id;
	private String naam;
	
	// CONSTRUCTORS
	/**
	 * Default constructor met waarden:
	 * <br> id = 0 
	 * <br> naam = "onbepaald"
	 */
	public Genre() {
		this.id = 0;
		this.naam = "onbepaald";
	}
	
	public Genre(long id, String naam) {
		this.id = id;
		this.naam = naam;
	}
	
	// GETTERS & SETTERS
	public long getId() {
		return id;
	}
	public void setId(long id) {	
		this.id = id;
	}
	public String getName() {
		return naam;
	}
	public void setName(String naam) {
		this.naam = naam;
	}
	
	
	// STANDARD OVERRIDE METHODS
	@Override
	public String toString() {
		return naam;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
		return result;
	}
	@Override
	/**
	 * Een genre is 'equal' aan een ander genre als het dezelfde naam heeft.
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genre other = (Genre) obj;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		return true;
	}
	

}
