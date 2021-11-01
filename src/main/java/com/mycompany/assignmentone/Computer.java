package com.mycompany.assignmentone;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class Computer {

    static Scanner input = new Scanner(System.in);
    static int max_threads = 3;

    public static void main(String args[]) {
        try {
            //COMPUTER AS A CLIENT
            InetAddress ip = InetAddress.getLocalHost();
            Socket server = new Socket(ip, 2000);

            DataInputStream serverRead = new DataInputStream(server.getInputStream()); //what client read from server
            DataOutputStream serverWrite = new DataOutputStream(server.getOutputStream()); //what client sends to the server

            //COMPUTER AS A SERVER
            ServerSocket computerServer = new ServerSocket(2500);
            while (true) {
                Socket client = computerServer.accept();
                System.out.println("Client Accepted");
                ClientHandler ch = new ClientHandler(client);
                Thread t = new Thread(ch);
                max_threads--;
                t.start();
                if (max_threads == 0) {
                    break;
                }

            }

            if (input.next().equals("send")) {
                String str;
                str = serverRead.readUTF();
                System.out.println(str);

                serverWrite.writeUTF(str);

                serverRead.close();
                serverWrite.close();
                server.close();

            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(Computer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Computer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
