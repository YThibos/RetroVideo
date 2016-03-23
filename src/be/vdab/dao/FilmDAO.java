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
import be.vdab.entities.Genre;

public class FilmDAO extends AbstractDAO {

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
	private static final String SQL_SELECT_TITEL = "SELECT films.id, films.genreid, films.titel, films.voorraad,"
			+ " films.gereserveerd, films.prijs"
			+ " FROM films"
			+ " WHERE films.titel=?";
	private static final String SQL_SELECT_ID = "SELECT " + ALL_FILM_FIELDS
			+ " FROM films WHERE films.id=?";
	
	private static final Logger logger = Logger.getLogger(GenreDAO.class.getName());
	
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
			throw new DAOException(ex);
		}
		
	}
	
	public List<Film> findMultipleIDs (List<Long> ids) {
		
		try (Connection connection = dataSource.getConnection();
				PreparedStatement sqlStatement = connection.prepareStatement(SQL_SELECT_ID)) {

			List<Film> foundFilms = new ArrayList<>();
			
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
			logger.log(Level.SEVERE, "Exception while getting data from Films in findByMultipleIds()", ex);
			throw new DAOException(ex);
		}
	}
	
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
			logger.log(Level.SEVERE, "Exception encountered while accessing Films in findByTitel()", ex);
			throw new DAOException(ex);
		}
		
	}
	
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
			logger.log(Level.SEVERE, "Exception while accesssing Films and Genres in findByGenre()", ex);
			throw new DAOException(ex);
		}
		
	}
	
	private Film mapResultRowToFilm(ResultSet results) throws SQLException {
		FilmBuilder newFilm = new FilmBuilder();
		 return newFilm
		.setId(results.getLong("films.id"))
		.setTitel(results.getString("films.titel"))
		.setGereserveerd(results.getInt("films.gereserveerd"))
		.setVoorraad(results.getInt("films.voorraad"))
		.setPrijs(new BigDecimal(results.getDouble("films.prijs")))
		.createFilm();
	}

	private Film mapResultRowToFilmWithGenre(ResultSet results) throws SQLException {
		
		Genre genre = new Genre(results.getLong("genres.id"), results.getString("genres.naam"));
		
		Film film = mapResultRowToFilm(results);
		film.setGenre(genre);
		
		return film;
	}
	
}
