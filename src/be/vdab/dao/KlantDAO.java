package be.vdab.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import be.vdab.entities.Klant;
import be.vdab.entities.KlantBuilder;

public class KlantDAO extends AbstractDAO {

	private static final String ALL_KLANT_FIELDS = "klanten.id, klanten.familienaam, klanten.voornaam,"
			+ " klanten.straatNummer, klanten.postcode, klanten.gemeente";
	
	
	public Klant mapResultRowToKlant (ResultSet results) throws SQLException {
		KlantBuilder klantBuilder = new KlantBuilder();
		
		return klantBuilder
		.setID(results.getLong("klanten.id"))
		.setFamilienaam(results.getString("klanten.familienaam"))
		.setVoornaam(results.getString("klanten.voornaam"))
		.setStraatnummer(results.getString("klanten.straatNummer"))
		.setPostcode(results.getString("klanten.postcode"))
		.setGemeente(results.getString("klanten.gemeente"))
		.createKlant();
	}
}
