package com.mycompany.assignmentone;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String args[]) {
        try {

            ServerSocket server = new ServerSocket(2000); //server initalize with port number 2000 to accept requests on
            Socket clientReciver = server.accept(); //wait for response from client

            DataInputStream clientRead = new DataInputStream(clientReciver.getInputStream()); //what client sends
            DataOutputStream clientWrite = new DataOutputStream(clientReciver.getOutputStream()); //send to client

            clientWrite.writeUTF("You are now connected to the Server!");
                String readdata;//wait for client to send some msgs
                readdata = clientRead.readUTF();

                System.out.println("Computer Collected Data Arrived ");

                        //close the terminals of the server
            clientRead.close();
            clientWrite.close();
            clientReciver.close();

            //server.close();
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
