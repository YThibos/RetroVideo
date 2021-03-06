package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import be.vdab.entities.Klant;
import be.vdab.entities.KlantBuilder;
import be.vdab.entities.KlantException;

/**
 * Data Access Object om klant data uit de retrovideo database te halen.
 * @author Yannick.Thibos
 *
 */
public class KlantDAO extends AbstractDAO {

	private static final String ALL_KLANT_FIELDS = "klanten.id, klanten.familienaam, klanten.voornaam,"
			+ " klanten.straatNummer, klanten.postcode, klanten.gemeente";
	
	private static final String SQL_SELECT_LIKE_FAMILIENAAM = "SELECT " + ALL_KLANT_FIELDS
			+ " FROM klanten"
			+ " WHERE klanten.familienaam LIKE ?";
	
	private static final String SQL_SELECT_ID = "SELECT " + ALL_KLANT_FIELDS
			+ " FROM klanten"
			+ " WHERE klanten.id = ?";
	
	private static final Logger logger = Logger.getLogger(KlantDAO.class.getName());
	
	
	public KlantDAO () { }
	
	/**
	 * Connecteert met de RetroVideo database en geeft een Klant terug die overeenkomt met opgegeven id.
	 * 
	 * @param id	De id van de te zoeken klant.
	 * @return		De gevonden Klant met als id die van de parameter, of null indien niet gevonden.
	 */
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
			logger.log(Level.SEVERE, "Exception bij het ophalen van data uit table klanten", ex);
			throw new DAOException(ex);
		}
		
	}
	
	/**
	 * Connecteert met de RetroVideo database en geeft een lijst van Klant objecten terug die een deel van de zoekstring
	 * in hun familienaam hebben.
	 * 
	 * @param naam		De zoekstring die in de familienaam moet voorkomen.
	 * @return			Een lijst van Klant objecten met gedeeltelijke match, of een lege lijst indien niets gevonden.
	 */
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
			logger.log(Level.SEVERE, "Exception bij het ophalen van data uit table klanten", ex);
			throw new DAOException(ex);			
		}
		
	}
	
	/**
	 * Haalt alle velden uit een ResultSet rij en returnt een ingevuld Klant object. 
	 * 
	 * @param results			Een ResultSet waarvan de cursor al wijst naar een rij.
	 * @return					Een Klant object ingevuld met de velden van de opgegeven ResultSet,
	 * 							of null wanneer een SQLException of een FilmException optreedt.
	 */
	public Klant mapResultRowToKlant (ResultSet results) {
		KlantBuilder klantBuilder = new KlantBuilder();
		
		try {
			return klantBuilder
			.setID(results.getLong("klanten.id"))
			.setFamilienaam(results.getString("klanten.familienaam"))
			.setVoornaam(results.getString("klanten.voornaam"))
			.setStraatnummer(results.getString("klanten.straatNummer"))
			.setPostcode(results.getString("klanten.postcode"))
			.setGemeente(results.getString("klanten.gemeente"))
			.createKlant();
		} catch (KlantException ex) {
			logger.log(Level.SEVERE, "Error bij mappen/aanmaken van Klant uit ResultSet", ex);
			return null;
		} catch (SQLException ex) {
			logger.log(Level.SEVERE, "Exception bij het ophalen van velden uit ResultSet", ex);
			return null;
		}
	}
}
