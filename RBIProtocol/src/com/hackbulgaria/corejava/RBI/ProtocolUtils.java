package com.hackbulgaria.corejava.RBI;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ProtocolUtils {

    public final String EOM = "<![End-of-message]!>";

    public String readFromSocket(Socket socket) throws IOException {

        StringBuilder result = new StringBuilder();
        Scanner scanner = new Scanner(socket.getInputStream());
        String line = "";
        while (!line.contains(EOM)) {
            line = scanner.nextLine();
            result.append(line);
            result.append(System.lineSeparator());
        }
        return result.toString();
    }

    public void writeToSocket(String message, Socket socket) throws IOException {
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        writer.println(message);
        writer.println(EOM);
        writer.flush();
    }
}