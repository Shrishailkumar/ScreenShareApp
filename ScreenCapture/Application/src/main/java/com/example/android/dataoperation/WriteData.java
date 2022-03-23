package com.example.android.dataoperation;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.ByteBuffer;

public class WriteData {
    OutputStream outStream;
   private static Socket socket;
    public static String host = "localhost";
    BufferedReader br = null;
    PrintWriter pw = null;
    WriteData() {

    }
    public static Socket getInstance(){
        if (null!=socket){
            try {
                socket = new Socket(host, 10001);
                return socket;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return socket;
    }

    public WriteData(Context ctx, OutputStream outStream,ByteBuffer byteBuffer) {
        initializeSocket(ctx,byteBuffer);
        this.outStream = outStream;
    }

    private void initializeSocket(Context ctx, ByteBuffer byteBuf) {
        try {
                // the code for sending the data to socket 10001
                if (outStream != null) {

                    try {

                        byte[] bytes = new byte[byteBuf.remaining()];
                        byteBuf.get(bytes);

                        //Send the data
                        outStream.write(bytes);
                        outStream.flush();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            // Create a socket that attempts to connect to the server
            // program on the host at port 10000.



            // Create an input stream reader that chains to the socket's
            // byte-oriented input stream. The input stream reader
            // converts bytes read from the socket to characters. The
            // conversion is based on the platform's default character
            // set.

            InputStreamReader isr;
            isr = new InputStreamReader(socket.getInputStream());

            // Create a buffered reader that chains to the input stream
            // reader. The buffered reader supplies a convenient method
            // for reading entire lines of text.

            br = new BufferedReader(isr);

            // Create a print writer that chains to the socket's byte-
            // oriented output stream. The print writer creates an
            // intermediate output stream writer that converts
            // characters sent to the socket to bytes. The conversion
            // is based on the platform's default character set.

            pw = new PrintWriter(socket.getOutputStream(), true);

            // Send the DATE command to the server.

            pw.println("DATE");

            // Obtain and print the current date/time.

            System.out.println(br.readLine());

            // the code for sending the data to socket 10001



        } catch (IOException e) {
            System.out.println(e.toString());
        } finally {
            try {
                if (br != null)
                    br.close();

                if (pw != null)
                    pw.close();

                if (socket != null)
                    socket.close();
            } catch (IOException e) {
            }
        }
    }

    void writeSampleData(final int trackIndex, final ByteBuffer byteBuf) {
        // if (mStatredCount > 0) {
        // mMediaMuxer.writeSampleData(trackIndex, byteBuf, bufferInfo);

        //      if (bufferInfo.size != 0) {

                /*byteBuf.position(bufferInfo.offset);
                byteBuf.limit(bufferInfo.offset + bufferInfo.size);*/

        if (outStream != null) {

            try {

                byte[] bytes = new byte[byteBuf.remaining()];
                byteBuf.get(bytes);

                //Send the data
                outStream.write(bytes);
                outStream.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //}
    //}
}
