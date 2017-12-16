package nz.net.daveshep.fermenter.localrouter;

import java.text.ParseException;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

/**
 * This is the Camel router that defines the following route: JMS Text messages
 * are received from the local queue, the messages body is transformed from a JSON
 * string to a POJO and then forwarded to a remote jms queue.
 * 
 * @author david
 * 
 */
public class LocalRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		// configure the dead letter channel to handle network outages and
		// resend messages
		errorHandler(deadLetterChannel("localjms:queue:dead")
				.maximumRedeliveries(-1)
				.retryAttemptedLogLevel(LoggingLevel.WARN)
				.redeliveryDelay(60000L));

		// handle ParseExceptions by just logging them
		onException(ParseException.class).log("got exception... ${body}");
		onException(UnrecognizedPropertyException.class).log("got exception... ${body}");
		
		// configure the local router
		from("localjms:queue:local") // this is the name of the local queue

				.log("got message: ${body}") // this logs the message received

				.to("remotejms:queue:prod-queue1"); // this is the final
													// destination of the
													// message

		
//				.to("bean:temperatureRecordToSql") // this creates a SQL INSERT
//													// statement from the POJO
//				.log("execute sql: ${body}")
//				
//				.to("jdbc:sqlite"); // this executes the SQL INSERT



	}

}
