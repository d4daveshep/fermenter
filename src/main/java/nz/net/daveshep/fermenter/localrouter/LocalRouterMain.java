package nz.net.daveshep.fermenter.localrouter;

import org.apache.camel.spring.Main;

public class LocalRouterMain {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		Main main = new Main(); // main class from camel-spring
		main.setApplicationContextUri("META-INF/spring/camel-activemq-server.xml");
//		main.enableHangupSupport();
		main.start();
		System.out
				.println("\n\nLocal Router application has now been started. Hit Ctrl-C to stop.\n\n");
	}

}
