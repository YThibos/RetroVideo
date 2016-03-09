package be.vdab.dao;

import javax.sql.DataSource;

abstract class AbstractDAO {
	
	public static final String JNDI_NAME = "jdbc/retrovideo"; 
	
	protected DataSource dataSource;
	
	public void setDataSource (DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
