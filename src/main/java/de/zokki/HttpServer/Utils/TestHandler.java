package de.zokki.HttpServer.Utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import de.zokki.HttpServer.HTTPServer;
import de.zokki.HttpServer.Configuration.ConfigurationManager;
import de.zokki.HttpServer.Logger.Logger;

public class TestHandler implements HttpHandler {
	
	private final Logger LOGGER = new Logger(HTTPServer.class, false);
	
	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		LOGGER.info("TestHandler starting...");
		
		httpExchange.getResponseHeaders().add("Content-type", "application/json");
		String response = new Gson().toJson(ConfigurationManager.getInstace().getCurrentConfiguation());
		httpExchange.sendResponseHeaders(200, response.length());
		
		BufferedOutputStream os = new BufferedOutputStream(httpExchange.getResponseBody());
		os.write(response.getBytes());
		os.flush();
		
		BufferedInputStream input = new BufferedInputStream(httpExchange.getRequestBody());
		int i;
		while((i = input.read()) != -1) {
			System.out.print((char) i);
		}
	}
}
