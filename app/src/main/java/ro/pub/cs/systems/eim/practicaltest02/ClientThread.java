package ro.pub.cs.systems.eim.practicaltest02;

/**
 * Created by bordin on 5/20/16.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.widget.TextView;

public class ClientThread extends Thread {

    public String address;
    public String port;
    public Socket socket;
    public TextView timeUtcNow;

    public ClientThread(String address, String port, TextView timeUtcNow) {
        this.address = address;
        this.port = port;
        this.timeUtcNow = timeUtcNow;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(address, Integer.parseInt(port));
            BufferedReader bufferedReader = Utilities.getReader(socket);
            String timeUtcNowString;
            while ((timeUtcNowString = bufferedReader.readLine()) != null) {
                final String finalTimeUtcNowString = timeUtcNowString;
                timeUtcNow.post(new Runnable() {
                    @Override
                    public void run() {
                        timeUtcNow.append(finalTimeUtcNowString + "\n");
                    }
                });
            }
            socket.close();
        } catch (NumberFormatException e) {
        e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
