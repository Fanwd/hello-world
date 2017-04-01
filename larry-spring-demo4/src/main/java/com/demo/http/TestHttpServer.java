package com.demo.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class TestHttpServer {

	public static void main(String[] args){
		
		try{
			
			InetSocketAddress inetAddr = new InetSocketAddress(9898);
			HttpServer http = HttpServer.create(inetAddr, 0);
			HttpHandler hand = new HttpHandler() {
				
				@Override
				public void handle(HttpExchange exchange) throws IOException {
					URI uri = exchange.getRequestURI();
					System.out.println("uri:"+uri.toString());
					Headers head = exchange.getResponseHeaders();
					head.add("Content-Type", "text/plain");
					exchange.sendResponseHeaders(200, 0);
					OutputStream os = exchange.getResponseBody();
					String str = "hello world!";
					os.write(str.getBytes("UTF-8"));
					os.close();
				}
			};
			http.setExecutor(Executors.newCachedThreadPool());
			http.createContext("/", hand);
			http.start();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
}
