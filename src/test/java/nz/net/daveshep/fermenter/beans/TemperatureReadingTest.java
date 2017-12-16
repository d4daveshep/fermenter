package nz.net.daveshep.fermenter.beans;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

public class TemperatureReadingTest {

	@Test
	public void testTimestamp() throws ParseException {

		String timestamp = "2016-06-28 18:39:26.123";
//		String timestamp = "2016-06-28 18:39:26.000";
		
		TemperatureReading reading = new TemperatureReading();
		reading.setBrewId("99-test-99");
/*
		reading.setAvgTemp(20.64);
		reading.setMaxTemp(21.31);
		reading.setMinTemp(18.84);
		reading.setNowTemp(20.34);
		reading.setTargetTemp(20);
		reading.setTimestamp(timestamp);
		
		assertEquals(timestamp, reading.getTimestampString());
*/		
	
		
	}

}
