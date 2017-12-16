package nz.net.daveshep.fermenter.remoterouter;

import java.text.ParseException;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

/**
 * This is the Camel router that defines the following route: JMS messages
 * containing serialised TemperatureReading objects are received from the local
 * router. The objects are persisted to a local SQL database.
 * 
 * @author david
 * 
 */
public class RemoteRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		// configure the dead letter channel to handle network outages and
		// resend messages
		/*
		 * errorHandler(deadLetterChannel("localjms:queue:dead")
		 * .maximumRedeliveries(-1) .retryAttemptedLogLevel(LoggingLevel.WARN)
		 * .redeliveryDelay(60000L));
		 * 
		 * // handle ParseExceptions by just logging them
		 * onException(ParseException.class).log("got exception... ${body}");
		 * onException(UnrecognizedPropertyException.class).log(
		 * "got exception... ${body}");
		 */
		// configure the remote router
		from("remotejms:queue:prod-queue1") // this is the name of the local
											// queue

				.unmarshal("json") // this unmarshals the Json message body to a
									// POJO (see config in the camel context)
				.log("POJO... ${bodyAs(nz.net.daveshep.fermenter.beans.TemperatureReading)}")

				.to("bean:temperatureRecordToSql") // this creates a SQL INSERT
													// statement from the POJO
				.log("execute sql: ${body}") // log the SQL statement

				// .to("jdbc:sqlite"); // this executes the SQL INSERT
				.to("jdbc:postgresql"); // this executes the SQL INSERT

		// .to("remotejms:queue:test-queue2"); // this is the final
		// destination of the
		// message

	}
}
