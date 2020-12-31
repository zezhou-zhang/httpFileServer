package com.zookeeper.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.zookeeper.http.config.Configuration;
import com.zookeeper.http.config.ConfigurationManager;

/**
 * 
 * @author Zezhou Zhang
 * 
 * Driver Class for HTTP Server
 *
 */
public class httpServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Server Starting...");
		
		ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
		Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();
		
		System.out.println("Using Port: " + conf.getPort());
		System.out.println("Using WebRoot: " + conf.getWebroot());
		
		ServerSocket serverSocket;
		try {
			//InetAddress addr = InetAddress.getLocalHost();
			serverSocket = new ServerSocket(conf.getPort());
			Socket socket = serverSocket.accept();
			
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			
			// TODO we would read
			
			// TODO writing
			
			String html = "<html><head><title>Server 1</title></head><body><h1>This page was served by server 1</h1></body></html>";
			
			final String CRLF = "\n\r"; // 13, 10
			String response = 
					"HTTP/1.1 200 OK" + CRLF + 	//status line: HTTP VERSION RESPONSE_CODE RESPONSE_MESSAGE
					"Content-Length: " + html.getBytes().length + CRLF +
					CRLF +
					html +
					CRLF + CRLF ;
			
			outputStream.write(response.getBytes());
			
			inputStream.close();
			outputStream.close();
			socket.close();
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
