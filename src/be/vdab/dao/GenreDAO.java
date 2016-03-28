package be.vdab.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import be.vdab.entities.Genre;

public class GenreDAO extends AbstractDAO {

	/**
	 * COLUMN NAMES OF THE GENRE TABLE
	 */
	public static final String COLUMN_ID = "genres.id";
	public static final String COLUMN_NAME = "genres.naam";
	public static final String ALL_GENRE_FIELDS = "genres.id, genres.naam";

	/**
	 * SQL QUERIES
	 */
	private static final String SQL_SELECT_GENRES = "SELECT " + ALL_GENRE_FIELDS + " FROM genres ORDER BY naam ASC";
	
	private static final Logger logger = Logger.getLogger(GenreDAO.class.getName());
	
	/**
	 * Connecteert met de database en geeft alle Genres terug die erin zitten, geordend op naam.
	 * 
	 * @return	Een lijst van alle Genres in de database.
	 */
	public List<Genre> findAllGenres() {
		
		try (Connection connection = dataSource.getConnection();
				Statement statement = connection.createStatement();) {
			
			// GENRES RARELY ALTERED: READ COMMITTED = SAFE ENOUGH
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			List<Genre> foundGenres = new ArrayList<>();
			
			try (ResultSet results = statement.executeQuery(SQL_SELECT_GENRES)) {
				
				
				while (results.next()) {
					foundGenres.add(mapResultRowToGenre(results));
				}
				
			}
			
			return foundGenres;
			
		}
		catch (SQLException ex ) {
			logger.log(Level.SEVERE, "Exception bij het ophalen van data uit table genres", ex);
			throw new DAOException(ex);
		}
		
	}
	
	/**
	 * Haalt alle velden uit een ResultSet rij en returnt een ingevuld Genre object. 
	 * 
	 * @param results			Een ResultSet waarvan de cursor al wijst naar een rij.
	 * @return					Een Genre object ingevuld met de velden van de opgegeven ResultSet,
	 * 							of null wanneer een SQLException optreedt.
	 */
	private Genre mapResultRowToGenre(ResultSet results) {
		try {
			return new Genre(results.getLong(COLUMN_ID), results.getString(COLUMN_NAME));
		} catch (SQLException ex) {
			logger.log(Level.SEVERE, "Exception bij het ophalen van de velden uit de ResultSet", ex);
			return null;
		}
	}
	
}
