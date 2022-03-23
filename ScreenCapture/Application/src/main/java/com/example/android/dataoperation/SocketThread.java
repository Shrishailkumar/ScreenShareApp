package com.example.android.dataoperation;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

class SocketThread implements Runnable {
    private ServerSocket serverSocket;
    private int SERVER_PORT = 10000;
    @Override
    public void run() {

        Socket socket;
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (null != serverSocket) {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    socket = serverSocket.accept();
                    CommunicationThread commThread = new CommunicationThread(socket);
                    new Thread(commThread).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    class CommunicationThread implements Runnable {

        InputStream in;
        DataInputStream dis;
        private Socket socket;

        public CommunicationThread(Socket clientSocket) {
            this.socket = clientSocket;

            System.out.println ("Server started, Accepting Connection...\n");
        }

        public void run() {

            while (!Thread.currentThread().isInterrupted()) {

                try {

                    byte[] data = new byte[512];

                } catch (Exception e) {

                    e.printStackTrace();

                    try {
                        //fos.close();
                    } catch (Exception e1) {

                        e1.printStackTrace();
                    }
                }
            }
        }
    }
}
