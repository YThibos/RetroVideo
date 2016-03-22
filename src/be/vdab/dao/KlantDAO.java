package be.vdab.dao;

import java.sql.ResultSet;

import be.vdab.entities.Klant;

public class KlantDAO extends AbstractDAO {

	private static final String ALL_KLANT_FIELDS = "klanten.id, klanten.familienaam, klanten.voornaam,"
			+ " klanten.straatNummer, klanten.postcode, klanten.gemeente";
	
	
	public Klant mapResultRowToKlant (ResultSet results) {
		
	}
}
