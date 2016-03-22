package be.vdab.entities;

public class KlantBuilder {
	
	private long id;
	private String familienaam;
	private String voornaam;
	private String straatnummer;
	private String postcode;
	private String gemeente;
	
	public KlantBuilder() {
	
	}
	
	public KlantBuilder setID(long id) {
		this.id= id;
		return this;
	}
	
	public KlantBuilder setFamilienaam (String familienaam) {
		this.familienaam = familienaam;
		return this;
	}
	
	public KlantBuilder setVoornaam (String voornaam) {
		this.voornaam = voornaam;
		return this;
	}
	
	public KlantBuilder setStraatnummer (String straatnummer) {
		this.straatnummer = straatnummer;
		return this;
	}
	
	public KlantBuilder setPostcode (String postcode) {
		this.postcode = postcode;
		return this;
	}
	
	public KlantBuilder setGemeente (String gemeente) {
		this.gemeente = gemeente;
		return this;
	}
	
	public Klant createKlant() {
		return new Klant(id, familienaam, voornaam, straatnummer, postcode, gemeente);
	}

}