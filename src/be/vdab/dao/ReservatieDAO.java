package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object om reservatie data in de retrovideo database weg te schrijven.
 * @author Yannick.Thibos
 *
 */
public class ReservatieDAO extends AbstractDAO {
	
	private static final String SQL_INSERT_RESERVATIE = "INSERT INTO reservaties (klantid, filmid, reservatieDatum)"
			+ " VALUES (?, ? , ?)";
	private static final String SQL_UPDATE_FILM_GERESERVEERD = "UPDATE films"
			+ " SET gereserveerd = gereserveerd + 1"
			+ " WHERE id = ? AND voorraad - gereserveerd > 0";
	
	// 
	
	private static final Logger logger = Logger.getLogger(ReservatieDAO.class.getName());
	
	/**
	 * Connecteert met de RetroVideo database en voegt een nieuwe reservatie toe in de tabel met reservaties.
	 * 
	 * @param filmid	De id van de film die gereserveerd wordt.
	 * @param klantid	De id van de klant die de film reserveert.
	 * @return			True wanneer de reservatie gelukt is, false bij falen van toevoegen.
	 */
	public boolean insertReservatie (long filmid, long klantid) {
		
		try (Connection connection = dataSource.getConnection();
				PreparedStatement sqlInsertStatement = connection.prepareStatement(SQL_INSERT_RESERVATIE);
				PreparedStatement sqlUpdateStatement = connection.prepareStatement(SQL_UPDATE_FILM_GERESERVEERD)) {
			
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			connection.setAutoCommit(false);
			
			
			sqlInsertStatement.setLong(1, klantid);
			sqlInsertStatement.setLong(2, filmid);
			sqlInsertStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
			int inserts = sqlInsertStatement.executeUpdate();
			
			sqlUpdateStatement.setLong(1, filmid);
			int updates = sqlUpdateStatement.executeUpdate();
			
			if (inserts == 1 && updates == 1) {
				connection.commit();
				return true;
			}
			else {
				connection.rollback();
				return false;
			}
			
		}
		catch (SQLException ex ) {
			logger.log(Level.SEVERE, "Exception bij het invoegen van data in de tabel reservaties", ex);
			throw new DAOException(ex);
		}
	}

}
