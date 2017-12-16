package nz.net.daveshep.fermenter.beans;

public class TemperatureReadingToSql {

	public String toSql(TemperatureReading reading) {
		
		String insertSQL = "INSERT INTO TEMPERATURE "
				+ "(BREWID, TARGET, AVG, MIN, MAX, ACTION, TIMESTAMP) "
				+ "VALUES (";
		
		insertSQL += "'" + reading.getBrewId() + "',";
		insertSQL += reading.getTargetTemp() + ",";
		insertSQL += reading.getAvgTemp() + ",";
		insertSQL += reading.getMinTemp() + ",";
		insertSQL += reading.getMaxTemp() + ",";
		insertSQL += "'" + reading.getAction() + "',";
		insertSQL += "'" + reading.getTimestampString() + "'";
		
		insertSQL += ")";

		return insertSQL;
		
	}
	
}
