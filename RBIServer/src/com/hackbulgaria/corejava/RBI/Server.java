package com.hackbulgaria.corejava.RBI;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

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
		Scanner scaner = new Scanner(process.getInputStream());
		String line = "";
		while (scaner.hasNextLine()) {
			line = scaner.nextLine();
			builder.append(line);
			builder.append("/n");
		}
		ProtocolUtils.writeToSocket(builder.toString(), clientSocket);
	}
}
