package nz.net.daveshep.fermenter.sql;

import static org.junit.Assert.fail;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.Test;

public class SqlInsertTest {

	@Test
	public void testSqlInsert() throws Exception {

		CamelContext camelContext = new DefaultCamelContext();
		camelContext.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				from("direct:start").log("test");
			}
		});

		String insertSql = "";
		
		camelContext.start();
		try {
			ProducerTemplate producer = camelContext.createProducerTemplate();

			String result = producer.requestBody("direct:start", insertSql,
					String.class);
			
			System.out.println("result="+result);

		} finally {
			camelContext.stop();
		}
	}

}
