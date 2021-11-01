/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignmentone;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author George
 */
public class Sensor {

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        try {
            //COMPUTER AS A CLIENT
            InetAddress ip = InetAddress.getLocalHost();
            Socket server = new Socket(ip, 2500);

            DataInputStream serverRead = new DataInputStream(server.getInputStream()); //what client read from server
            DataOutputStream serverWrite = new DataOutputStream(server.getOutputStream()); //what client sends to the server

            String str = serverRead.readUTF();
            System.out.println(str);
            // System.out.println(str);
            while (true) {
                String user_message = input.next();
                if (user_message.equals("bye")) {
                    System.out.println("Session Ended");
                    serverWrite.writeUTF(user_message);
                    serverWrite.flush();
                    break;
                }

            }
            serverRead.close();
            serverWrite.close();
            server.close();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Computer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Computer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
