package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import be.vdab.entities.Klant;
import be.vdab.entities.KlantBuilder;

public class KlantDAO extends AbstractDAO {

	private static final String ALL_KLANT_FIELDS = "klanten.id, klanten.familienaam, klanten.voornaam,"
			+ " klanten.straatNummer, klanten.postcode, klanten.gemeente";
	
	private static final String SQL_SELECT_LIKE_FAMILIENAAM = "SELECT " + ALL_KLANT_FIELDS
			+ " FROM klanten"
			+ " WHERE klanten.familienaam LIKE ?";
	
	private static final String SQL_SELECT_ID = "SELECT " + ALL_KLANT_FIELDS
			+ " FROM klanten"
			+ " WHERE klanten.id = ?";
	
	
	public KlantDAO () { }
	
	public Klant findByID (long id) {
		
		try (Connection connection = dataSource.getConnection();
				PreparedStatement sqlStatement = connection.prepareStatement(SQL_SELECT_ID)) {
			
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			
			sqlStatement.setLong(1, id);
			
			Klant foundKlant = null;
			
			try (ResultSet results = sqlStatement.executeQuery()) {
				if (results.next())	foundKlant = mapResultRowToKlant(results);
			}
			
			return foundKlant;
			
		}
		catch (SQLException ex ) {
			throw new DAOException(ex);
		}
		
	}
	
	public List<Klant> findByFamilienaam (String zoekstring) {
		
		try (Connection connection = dataSource.getConnection();
				PreparedStatement sqlStatement = connection.prepareStatement(SQL_SELECT_LIKE_FAMILIENAAM)) {
			
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			
			zoekstring = "%" + zoekstring + "%";
			sqlStatement.setString(1, zoekstring);
			
			List<Klant> foundKlanten = new ArrayList<>();
			
			try (ResultSet results = sqlStatement.executeQuery()) {
				while (results.next()) {
					foundKlanten.add(mapResultRowToKlant(results));
				}
			}
			
			return foundKlanten;
			
		}
		catch (SQLException ex ) {
			throw new DAOException(ex);			
		}
		
	}
	
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
