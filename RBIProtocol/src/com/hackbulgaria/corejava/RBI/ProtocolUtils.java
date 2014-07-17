package com.hackbulgaria.corejava.RBI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ProtocolUtils {

    public static final String EOM = "<![End-of-message]!>";

    public static String readFromSocket(Socket socket) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        StringBuilder result = new StringBuilder();
        String line = "";
		while ((line = bufferedReader.readLine()) != null) {
			if (line.contains(EOM)) {
				break;
			}
            result.append(line).append(System.lineSeparator());
        }
        return result.toString();
    }

    public static void writeToSocket(String message, Socket socket) throws IOException {
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        writer.println(message);
        writer.println(EOM);
        writer.flush();
    }
}