package com.hackbulgaria.corejava.RBI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException,
			InterruptedException {
		StringBuilder builder = new StringBuilder();
		int portNumber = 1436;
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(portNumber);
		Socket clientSocket = serverSocket.accept();
		Process process = Runtime.getRuntime().exec(ProtocolUtils.readFromSocket(clientSocket));
		process.waitFor();
		@SuppressWarnings("resource")
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(process.getInputStream()));
		String line = "";
		while ((line = bufferedReader.readLine()) != null) {
			builder.append(line);
			builder.append("/n");
		}
		ProtocolUtils.writeToSocket(builder.toString(), clientSocket);
	}
}
