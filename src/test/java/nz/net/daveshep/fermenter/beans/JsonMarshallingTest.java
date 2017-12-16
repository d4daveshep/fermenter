package nz.net.daveshep.fermenter.beans;

import static org.junit.Assert.*;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.junit.Assert;
import org.junit.Test;

public class JsonMarshallingTest {

	/**
	 * Test marshalling a TemperatureReading object to a JSON string
	 * 
	 * @throws Exception
	 */
	@Test
	public void testJsonMarshall() throws Exception {
		CamelContext camelContext = new DefaultCamelContext();
		camelContext.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				from("direct:start").marshal().json(JsonLibrary.Jackson);
			}
		});

		// here's the json string we're expecting to get from the reading object
		String jsonString = "{'timestamp':'2016-06-28 18:39:26.679','brewid':'99-test-99','action':'Cool','target':20.0,'min':18.84,'max':21.31,'now':20.13,'avg':20.64}";
		// String expected =
		// "{'max': 21.31, 'timestamp': '2016-06-28 18:39:26.679', 'avg': 20.64, 'brewid': '99-test-99', 'target': 20, 'min': 18.84}";

		camelContext.start();
		try {
			ProducerTemplate producer = camelContext.createProducerTemplate();

			TemperatureReading reading = new TemperatureReading();
			reading.setBrewId("99-test-99");
			reading.setAction("Cool");

			reading.setAvgTemp(20.64);
			reading.setMaxTemp(21.31);
			reading.setMinTemp(18.84);
			reading.setTargetTemp(20);
			reading.setNowTemp(20.13);
			reading.setTimestamp("2016-06-28 18:39:26.679");

			String result = producer.requestBody("direct:start", reading,
					String.class);

			assertEquals(jsonString.replace('\'', '"'), result);

		} finally {
			camelContext.stop();
		}
	}

	@Test
	public void testJsonUnmarshall() throws Exception {

		final JacksonDataFormat format = new JacksonDataFormat(
				TemperatureReading.class);

		CamelContext camelContext = new DefaultCamelContext();
		camelContext.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				// from("direct:start").unmarshal().json(JsonLibrary.Jackson,TemperatureReading.class);
				from("direct:start").unmarshal(format);
			}
		});

		String jsonString = "{'timestamp':'2016-06-28 18:39:26.679','brewid':'99-test-99','action':'Cool','target':20.0,'min':18.84,'max':21.31,'avg':20.64,'now':20.13}";
		// String jsonString = "{'" + TemperatureReading.class.getName() +
		// "':{'timestamp':'2016-06-28 18:39:26.679','brewid':'99-test-99','target':20.0,'min':18.84,'max':21.31,'avg':20.64}}";

		camelContext.start();
		try {
			ProducerTemplate producer = camelContext.createProducerTemplate();

			TemperatureReading reading = producer.requestBody("direct:start",
					jsonString.replace('\'', '"'), TemperatureReading.class);

			assertEquals("2016-06-28 18:39:26.679", reading.getTimestampString());
			assertEquals("99-test-99", reading.getBrewId());
			assertTrue(20.0 == reading.getTargetTemp());
			assertEquals("Cool", reading.getAction());
			assertTrue(20.13 == reading.getNowTemp());

		} finally {
			camelContext.stop();
		}

	}
}
