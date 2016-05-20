package ro.pub.cs.systems.eim.practicaltest02;

/**
 * Created by bordin on 5/20/16.
 */
import android.util.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ServerThread extends Thread {

    private int port;
    public HashMap<String, TimeNow> data = null;
    private ServerSocket serverSocket = null;


    public ServerThread (int port) {
        this.port = port;
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.data = new HashMap<>();
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public synchronized void removeData(String ipAddress) {
        this.data.remove(ipAddress);
    }
    public synchronized TimeNow getData(String ipAddress) {
        return data.get(ipAddress);
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                Log.i("[practicaltest02]", "[SERVER] A connection request was received from " + socket.getInetAddress() + ":" + socket.getLocalPort());
            } catch (IOException e) {
                e.printStackTrace();
            }

            CommunicationThread communicationThread = new CommunicationThread(this, socket);
            communicationThread.start();
        }

    }


}