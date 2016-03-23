package be.vdab.entities;

/**
 * Builder voor de Klant class. Alle velden zijn verplicht in te vullen via de setter methods.
 * Zoniet zal createKlant een exception throwen.
 * @author Yannick.Thibos
 *
 */
public class KlantBuilder {
	
	private long id;
	private String familienaam;
	private String voornaam;
	private String straatnummer;
	private String postcode;
	private String gemeente;
	
	public KlantBuilder() {
		this.id = -1;
		this.familienaam = this.voornaam = this.straatnummer 
				= this.postcode = this.gemeente = null;
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
	
	/**
	 * Creëer een nieuwe Klant met de attributen ingevuld door de setters v/d Builder.
	 * Alle velden moeten eerst gezet worden voordat createFilm succesvol een Klant returnt.
	 * 
	 * @return Een Klant object met attributen gezet door de setters van de KlantBuilder.
	 * @throws KlantException Thrown wanneer minstens één setter werd overgeslagen.
	 */
	public Klant createKlant() throws KlantException {
		if (id != -1 && familienaam != null && voornaam != null && straatnummer != null && postcode != null && gemeente != null) {
			return new Klant(id, familienaam, voornaam, straatnummer, postcode, gemeente);	
		}
		else {
			throw new KlantException("Een van de velden werd niet ingevuld in de KlantBuilder");
		}
	}

}
