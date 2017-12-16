package nz.net.daveshep.fermenter.sql;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class CreateTemperatureTable {
	protected DataSource dataSource;
	protected JdbcTemplate jdbc;

	public CreateTemperatureTable(DataSource ds) {
//		log.info("CreateTable constructor called");
		setDataSource(ds);
		setUpTable();
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	public void setUpTable() {
//		log.info("About to set up table...");
		jdbc = new JdbcTemplate(dataSource);
		String sql = "CREATE TABLE IF NOT EXISTS TEMPERATURE "
				+ "(ID SERIAL PRIMARY KEY, " //use this for postgresql
//				+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " // use this for sqlite
				+ " BREWID         TEXT    NOT NULL, "
				+ " TARGET         REAL    NOT NULL, "
				+ " AVG            REAL    NOT NULL, "
				+ " MIN            REAL    NOT NULL, "
				+ " MAX            REAL    NOT NULL, "
				+ " ACTION         TEXT    NOT NULL, "
				+ " TIMESTAMP      TEXT    NOT NULL)";
		jdbc.execute(sql);
//		log.info("Table created");
	}
}
