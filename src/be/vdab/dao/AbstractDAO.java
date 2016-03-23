package be.vdab.dao;

import javax.sql.DataSource;

/**
 * Abstracte DAO class waarvan alle DAO's erven, met placeholder dataSource setter en de JNDI naam als public static String.
 * @author Yannick.Thibos
 *
 */
abstract class AbstractDAO {
	
	public static final String JNDI_NAME = "jdbc/retrovideo"; 
	
	protected DataSource dataSource;
	
	public void setDataSource (DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
