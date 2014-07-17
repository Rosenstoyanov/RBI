package com.hackbulgaria.corejava.RBI;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        String hostName = "192.168.1.45";
        int portNumber = 1436;

        try (Socket clientSocket = new Socket(hostName, portNumber);) {
            clientSocket.connect(new InetSocketAddress(hostName, portNumber));
            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine();
            ProtocolUtils.writeToSocket(message, clientSocket);

            while (scanner.hasNextLine()) {
                System.out.println(ProtocolUtils.readFromSocket(clientSocket));
            }

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }
}