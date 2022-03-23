package com.example.android.TestCode;

import java.io.*;
import java.net.*;
import java.util.*;

class SSServer
{
    public static void main (String [] args) throws IOException
    {
        System.out.println ("Server starting...\n");

        // Create a server socket that listens for incoming connection
        // requests on port 10000.

        ServerSocket server = new ServerSocket (10001);

        while (true)
        {
            // Listen for incoming connection requests from client
            // programs, establish a connection, and return a Socket
            // object that represents this connection.

            Socket s = server.accept ();

            System.out.println ("Accepting Connection...\n");

            // Start a thread to handle the connection.

            new ServerThread (s).start ();
        }
    }
}

class ServerThread extends Thread
{
    private Socket s;

    ServerThread (Socket s)
    {
        this.s = s;
    }

    public void run ()
    {
        BufferedReader br = null;
        PrintWriter pw = null;

        try
        {
            // Create an input stream reader that chains to the socket's
            // byte-oriented input stream. The input stream reader
            // converts bytes read from the socket to characters. The
            // conversion is based on the platform's default character
            // set.

            InputStreamReader isr;
            isr = new InputStreamReader (s.getInputStream ());

            // Create a buffered reader that chains to the input stream
            // reader. The buffered reader supplies a convenient method
            // for reading entire lines of text.

            br = new BufferedReader (isr);

            // Create a print writer that chains to the socket's byte-
            // oriented output stream. The print writer creates an
            // intermediate output stream writer that converts
            // characters sent to the socket to bytes. The conversion
            // is based on the platform's default character set.

            pw = new PrintWriter (s.getOutputStream (), true);

            // Create a calendar that makes it possible to obtain date
            // and time information.

            Calendar c = Calendar.getInstance ();

            // Because the client program may send multiple commands, a
            // loop is required. Keep looping until the client either
            // explicitly requests termination by sending a command
            // beginning with letters BYE or implicitly requests
            // termination by closing its output stream.

            do
            {
                // Obtain the client program's next command.

                String cmd = br.readLine ();

                // Exit if client program has closed its output stream.

                if (cmd == null)
                    break;

                // Convert command to uppercase, for ease of comparison.

                cmd = cmd.toUpperCase ();

                // If client program sends BYE command, terminate.

                if (cmd.startsWith ("BYE"))
                    break;

                // If client program sends DATE or TIME command, return
                // current date/time to the client program.

                if (cmd.startsWith ("DATE") || cmd.startsWith ("TIME"))
                    pw.println (c.getTime ().toString ());

                // If client program sends DOM (Day Of Month) command,
                // return current day of month to the client program.

                if (cmd.startsWith ("DOM"))
                    pw.println ("" + c.get (Calendar.DAY_OF_MONTH));

                // If client program sends DOW (Day Of Week) command,
                // return current weekday (as a string) to the client
                // program.

                if (cmd.startsWith ("DOW"))
                    switch (c.get (Calendar.DAY_OF_WEEK))
                    {
                        case Calendar.SUNDAY   : pw.println ("SUNDAY");
                            break;

                        case Calendar.MONDAY   : pw.println ("MONDAY");
                            break;

                        case Calendar.TUESDAY  : pw.println ("TUESDAY");
                            break;

                        case Calendar.WEDNESDAY: pw.println ("WEDNESDAY");
                            break;

                        case Calendar.THURSDAY : pw.println ("THURSDAY");
                            break;

                        case Calendar.FRIDAY   : pw.println ("FRIDAY");
                            break;

                        case Calendar.SATURDAY : pw.println ("SATURDAY");
                    }

                // If client program sends DOY (Day of Year) command,
                // return current day of year to the client program.

                if (cmd.startsWith ("DOY"))
                    pw.println ("" + c.get (Calendar.DAY_OF_YEAR));

                // If client program sends PAUSE command, sleep for three
                // seconds.

                if (cmd.startsWith ("PAUSE"))
                    try
                    {
                        Thread.sleep (3000);
                    }
                    catch (InterruptedException e)
                    {
                    }
            }
            while (true);
        }
        catch (IOException e)
        {
            System.out.println (e.toString ());
        }
        finally
        {
            System.out.println ("Closing Connection...\n");

            try
            {
                if (br != null)
                    br.close ();

                if (pw != null)
                    pw.close ();

                if (s != null)
                    s.close ();
            }
            catch (IOException e)
            {
            }
        }
    }
}