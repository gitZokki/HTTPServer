package de.zokki.HttpServer.Utils;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Date;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class DateHandler implements HttpHandler {
	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		httpExchange.getResponseHeaders().add("Content-type", "text/html");
		String response = "<b>" + new Date() + "</b> for " + httpExchange.getRequestURI();
		httpExchange.sendResponseHeaders(200, response.length());

		BufferedOutputStream output = new BufferedOutputStream(httpExchange.getResponseBody());
		output.write(response.getBytes());
		output.flush();
		output.close();
	}
}
