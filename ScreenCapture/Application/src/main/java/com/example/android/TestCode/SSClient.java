package com.example.android.TestCode;

import java.io.*;
import java.net.*;

public class SSClient {

    public static void main(String[] args) {
        String host = "localhost";

        // If user specifies a command-line argument, that argument
        // represents the host name.

        if (args.length == 1)
            host = args[0];

        BufferedReader br = null;
        PrintWriter pw = null;
        Socket s = null;

        try {
            // Create a socket that attempts to connect to the server
            // program on the host at port 10000.

            s = new Socket(host, 10000);

            // Create an input stream reader that chains to the socket's
            // byte-oriented input stream. The input stream reader
            // converts bytes read from the socket to characters. The
            // conversion is based on the platform's default character
            // set.

            InputStreamReader isr;
            isr = new InputStreamReader(s.getInputStream());

            // Create a buffered reader that chains to the input stream
            // reader. The buffered reader supplies a convenient method
            // for reading entire lines of text.

            br = new BufferedReader(isr);

            // Create a print writer that chains to the socket's byte-
            // oriented output stream. The print writer creates an
            // intermediate output stream writer that converts
            // characters sent to the socket to bytes. The conversion
            // is based on the platform's default character set.

            pw = new PrintWriter(s.getOutputStream(), true);

            // Send the DATE command to the server.

            pw.println("DATE");

            // Obtain and print the current date/time.

            System.out.println(br.readLine());

            // Send the PAUSE command to the server. This allows several
            // clients to start and verifies that the server is spawning
            // multiple threads.

            pw.println("PAUSE");

            // Send the DOW command to the server.

            pw.println("DOW");

            // Obtain and print the current day of week.

            System.out.println(br.readLine());

            // Send the DOM command to the server.

            pw.println("DOM");

            // Obtain and print the current day of month.

            System.out.println(br.readLine());

            // Send the DOY command to the server.

            pw.println("DOY");

            // Obtain and print the current day of year.

            System.out.println(br.readLine());
        } catch (IOException e) {
            System.out.println(e.toString());
        } finally {
            try {
                if (br != null)
                    br.close();

                if (pw != null)
                    pw.close();

                if (s != null)
                    s.close();
            } catch (IOException e) {
            }
        }
    }
}
