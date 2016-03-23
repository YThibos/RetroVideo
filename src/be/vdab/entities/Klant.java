package be.vdab.entities;

public class Klant {
	
	private long id;
	private String familienaam;
	private String voornaam;
	private String straatNummer;
	private String postcode;
	private String gemeente;
	
	// CONSTRUCTORS
	public Klant() {
		this.id = -1;
		this.familienaam = "niet ingevuld";
		this.voornaam = "niet ingevuld";
		this.straatNummer = "niet ingevuld";
		this.postcode = "niet ingevuld";
		this.gemeente = "niet ingevuld";
	}
	
	public Klant(long id, String familienaam, String voornaam, String straatNummer, String postcode, String gemeente) {
		this.id = id;
		this.familienaam = familienaam;
		this.voornaam = voornaam;
		this.straatNummer = straatNummer;
		this.postcode = postcode;
		this.gemeente = gemeente;
	}
	
	// GETTERS & SETTERS
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFamilienaam() {
		return familienaam;
	}
	public void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}
	public String getVoornaam() {
		return voornaam;
	}
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}
	public String getStraatNummer() {
		return straatNummer;
	}
	public void setStraatNummer(String straatNummer) {
		this.straatNummer = straatNummer;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getGemeente() {
		return gemeente;
	}
	public void setGemeente(String gemeente) {
		this.gemeente = gemeente;
	}

	// DEFAULT OVERRIDE METHODS
	@Override
	public String toString() {
		return "Klant [id=" + id + ", familienaam=" + familienaam + ", voornaam=" + voornaam + ", straatNummer="
				+ straatNummer + ", postcode=" + postcode + ", gemeente=" + gemeente + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((familienaam == null) ? 0 : familienaam.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((voornaam == null) ? 0 : voornaam.hashCode());
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
		Klant other = (Klant) obj;
		if (familienaam == null) {
			if (other.familienaam != null)
				return false;
		} else if (!familienaam.equals(other.familienaam))
			return false;
		if (id != other.id)
			return false;
		if (voornaam == null) {
			if (other.voornaam != null)
				return false;
		} else if (!voornaam.equals(other.voornaam))
			return false;
		return true;
	}
	
	

}
