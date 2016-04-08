package be.vdab.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import be.vdab.entities.Film;
import be.vdab.entities.FilmBuilder;
import be.vdab.entities.FilmException;
import be.vdab.entities.Genre;

/**
 * Data Access Object om film data uit de retrovideo database te halen.
 *
 * @author Yannick.Thibos
 *
 */
public class FilmDAO extends AbstractDAO {

	public static final String COLUMN_ID = "films.id";
	public static final String COLUMN_GENREID = "films.genreid";
	public static final String COLUMN_TITEL = "films.titel";
	public static final String COLUMN_VOORRAAD = "films.voorraad";
	public static final String COLUMN_GERESERVEERD = "films.gereserveerd";
	public static final String COLUMN_PRIJS = "films.prijs";
	
	public static final String ALL_FILM_FIELDS = "films.id, films.genreid, films.titel, "
			+ "films.voorraad, films.gereserveerd, films.prijs";

	/**
	 * SQL QUERIES
	 */
	private static final String SQL_SELECT_GENRE = "SELECT " + ALL_FILM_FIELDS
			+ ", "+ GenreDAO.ALL_GENRE_FIELDS
			+ " FROM films INNER JOIN genres ON films.genreid=genres.id"
			+ " WHERE genres.naam=?"
			+ " ORDER BY films.titel ASC";
	private static final String SQL_SELECT_TITEL = "SELECT " + ALL_FILM_FIELDS
			+ " FROM films"
			+ " WHERE films.titel=?";
	private static final String SQL_SELECT_ID = "SELECT " + ALL_FILM_FIELDS
			+ " FROM films WHERE films.id=?";
	
	private static final Logger logger = Logger.getLogger(GenreDAO.class.getName());
	
	/**
	 * Connecteert met de RetroVideo database en geeft een Film terug die overeenkomt met opgegeven id.
	 * 
	 * @param id	De id van de te zoeken film.
	 * @return		De gevonden Film met als id die van de parameter, of null indien niet gevonden.
	 */
	public Film findByID (long id) {
		
		try (Connection connection = dataSource.getConnection();
				PreparedStatement sqlStatement = connection.prepareStatement(SQL_SELECT_ID)) {
			
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			
			sqlStatement.setLong(1, id);
			
			Film foundFilm = null;
			
			try (ResultSet results = sqlStatement.executeQuery()) {
				if (results.next()) foundFilm = mapResultRowToFilm(results);
			}
			
			return foundFilm;
			
		}
		catch (SQLException ex ) {
			logger.log(Level.SEVERE, "Exception bij het ophalen van data uit table films", ex);
			throw new DAOException(ex);
		}
		
	}
	
	/**
	 * Connecteert met de RetroVideo database en geeft een lijst van Film objecten terug met id's die overeenkomen
	 * in de meegegeven lijst van id's.
	 * 
	 * @param ids	De lijst van id's van de te zoeken films.
	 * @return		Een lijst van gevonden Film objecten met als id's die van de parameter,
	 * 				of een lege lijst indien niets gevonden.
	 */
	public List<Film> findMultipleIDs (List<Long> ids) {
		
		try (Connection connection = dataSource.getConnection();
				PreparedStatement sqlStatement = connection.prepareStatement(SQL_SELECT_ID)) {

			List<Film> foundFilms = new ArrayList<>();
			
			// SELECT QUERY ONLY EXECUTED ONCE: READ_COMMITTED SAFE ENOUGH
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			
			for (Long id : ids) {
				sqlStatement.setLong(1, id);
				try (ResultSet results = sqlStatement.executeQuery()) {
					while (results.next()) {
						foundFilms.add(mapResultRowToFilm(results));
					}
				}
			}
			
			connection.commit();
			
			return foundFilms;
			
		}
		catch (SQLException ex ) {
			logger.log(Level.SEVERE, "Exception bij het ophalen van data uit table films", ex);
			throw new DAOException(ex);
		}
	}
	
	/**
	 * Connecteert met de RetroVideo database en geeft een Film terug die overeenkomt met opgegeven film titel.
	 * 
	 * @param titel		De te zoeken titel in de database.
	 * @return			De Film met overeenkomstige titel, of null indien niet gevonden.
	 */
	public Film findByTitel (String titel) {
		
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_SELECT_TITEL)) {
			
			// SELECT QUERY ONLY EXECUTED ONCE: READ_COMMITTED SAFE ENOUGH
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			
			statement.setString(1, titel);
			
			Film foundFilm = null;
			
			try (ResultSet results = statement.executeQuery()) {
				if (results.next()) foundFilm = mapResultRowToFilm(results) ;
			}
			
			return foundFilm;
		}
		catch (SQLException ex ) {
			logger.log(Level.SEVERE, "Exception bij het ophalen van data uit table films", ex);
			throw new DAOException(ex);
		}
		
	}
	
	/**
	 * Connecteert met de RetroVideo database en geeft een lijst van Film objecten terug van het opgegeven genre.
	 * 
	 * @param naam		De naam van het genre waarop gezocht wordt.
	 * @return			Een lijst van Film objecten met overeenkomstig genre, of een lege lijst indien niets gevonden.
	 */
	public List<Film> findByGenre (String naam) {
		
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_SELECT_GENRE)) {
			
			// SELECT QUERY ONLY EXECUTED ONCE: READ_COMMITTED SAFE ENOUGH
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			
			List<Film> foundFilms = new ArrayList<>();
			
			statement.setString(1, naam);
			
			try (ResultSet results = statement.executeQuery()) {
				
				while (results.next()) {
					foundFilms.add(mapResultRowToFilmWithGenre(results));
				}
				
			}
			
			return foundFilms;
			
		}
		catch (SQLException ex ) {
			logger.log(Level.SEVERE, "Exception bij het ophalen van data uit table films", ex);
			throw new DAOException(ex);
		}
		
	}
	
	/**
	 * Haalt alle velden uit een ResultSet rij en returnt een ingevuld Film object. Genre krijgt de waarden van
	 * de default constructor van een Genre.
	 * 
	 * @param results			Een ResultSet waarvan de cursor al wijst naar een rij.
	 * @return					Een Film object ingevuld met de velden van de opgegeven ResultSet,
	 * 							of null wanneer een SQLException of een FilmException optreedt.
	 */
	private Film mapResultRowToFilm(ResultSet results) {
		
		FilmBuilder newFilm = new FilmBuilder();
		try {
			return newFilm
			.setId(results.getLong(COLUMN_ID))
			.setTitel(results.getString(COLUMN_TITEL))
			.setGereserveerd(results.getInt(COLUMN_GERESERVEERD))
			.setVoorraad(results.getInt(COLUMN_VOORRAAD))
			.setPrijs(new BigDecimal(results.getDouble(COLUMN_PRIJS)))
			.setGenre(new Genre())
			.createFilm();
		} catch (SQLException ex) {
			logger.log(Level.SEVERE, "Exception bij het ophalen van velden uit ResultSet", ex);
			return null;
		} catch (FilmException ex) {
			logger.log(Level.SEVERE, "Exception bij het creëren van Film object met FilmBuilder", ex);
			return null;
		}
		 
	}

	/**
	 * Haalt alle velden uit een ResultSet rij en returnt een ingevuld Film object. Genre is ingevuld met overeenkomstige
	 * Genre waarden uit de database.
	 * 
	 * @param results			Een ResultSet waarvan de cursor al wijst naar een rij.
	 * @return					Een Film object ingevuld met de velden van de opgegeven ResultSet,
	 * 							of null wanneer een SQLException of een FilmException optreedt.
	 */
	private Film mapResultRowToFilmWithGenre(ResultSet results) {
		
		Genre genre;
		try {
			genre = new Genre(results.getLong(GenreDAO.COLUMN_ID), results.getString(GenreDAO.COLUMN_NAME));
			Film film = mapResultRowToFilm(results);
			film.setGenre(genre);
			return film;
		} catch (SQLException ex) {
			logger.log(Level.SEVERE, "Exception bij het ophalen van velden uit ResultSet", ex);
			return null;
		}
		
		
	}
	
}
