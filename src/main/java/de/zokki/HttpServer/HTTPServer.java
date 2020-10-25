package de.zokki.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import de.zokki.HttpServer.Configuration.Configuration;
import de.zokki.HttpServer.Configuration.ConfigurationManager;
import de.zokki.HttpServer.Logger.Logger;
import de.zokki.HttpServer.Utils.DateHandler;
import de.zokki.HttpServer.Utils.TestHandler;

public class HTTPServer {
	
	private final Logger LOGGER = new Logger(HTTPServer.class, false);
	
	static ConfigurationManager configManager = ConfigurationManager.getInstace();
	static Configuration config = null;
	
	public static void main(String[] args) {
		new HTTPServer();
	}
	
	public HTTPServer() {
		LOGGER.info("Server starting...");
		
		configManager.loadConfiguationFile("src/main/resources/http.json");
		config = configManager.getCurrentConfiguation();
		
		LOGGER.info("Server-Port: ", config.getPort());
		LOGGER.info("Server-Webroot: ", config.getWebroot());
		
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(config.getPort()), 0);
			server.createContext("/", new DateHandler());
			server.createContext("/test", new TestHandler());
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
