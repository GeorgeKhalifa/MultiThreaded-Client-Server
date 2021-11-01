/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignmentone;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author George
 */
class ClientHandler implements Runnable {

    Socket s;

    public ClientHandler(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            out.writeUTF("You are Connected To Computer");
            while (true) {
                String choice = in.readUTF();
                if (choice.equals("bye")) {
                    System.out.println("Session Ended");
                    out.writeUTF("bye");
                    break;
                }
            }
            
            in.close();
            out.close();
            s.close();
            

        } catch (Exception e) {
        }

    }

}
