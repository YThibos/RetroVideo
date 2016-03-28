package be.vdab.entities;

import java.time.LocalDate;

/**
 * Class die een Reservatie en zijn eigenschappen beschrijft, conform de eigenschappen zoals in de RetroVideo database.
 * 
 * @author Yannick.Thibos
 *
 */
public class Reservatie {
	
	private long klantid;
	private long filmid;
	private LocalDate reservatieDatum;
	
	// CONSTRUCTORS
	public Reservatie() {
		this.klantid = -1;
		this.filmid = -1;
		this.reservatieDatum = null;
	}
	
	public Reservatie(long klantid, long filmid, LocalDate reservatieDatum) {
		this.klantid = klantid;
		this.filmid = filmid;
		this.reservatieDatum = reservatieDatum;
	}

	// GETTERS & SETTERS
	public long getKlantid() {
		return klantid;
	}

	public void setKlantid(long klantid) {
		this.klantid = klantid;
	}

	public long getFilmid() {
		return filmid;
	}

	public void setFilmid(long filmid) {
		this.filmid = filmid;
	}

	public LocalDate getReservatieDatum() {
		return reservatieDatum;
	}

	public void setReservatieDatum(LocalDate reservatieDatum) {
		this.reservatieDatum = reservatieDatum;
	}

	// STANDARD OVERRIDE METHODS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (filmid ^ (filmid >>> 32));
		result = prime * result + (int) (klantid ^ (klantid >>> 32));
		result = prime * result + ((reservatieDatum == null) ? 0 : reservatieDatum.hashCode());
		return result;
	}

	@Override
	/**
	 * Een reservatie is 'equal' aan een andere reservatie als het dezelfde timestamp als reservatieDatum heeft.
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservatie other = (Reservatie) obj;
		if (filmid != other.filmid)
			return false;
		if (klantid != other.klantid)
			return false;
		if (reservatieDatum == null) {
			if (other.reservatieDatum != null)
				return false;
		} else if (!reservatieDatum.equals(other.reservatieDatum))
			return false;
		return true;
	}
	
	

}
