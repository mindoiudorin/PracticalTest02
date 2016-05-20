package ro.pub.cs.systems.eim.practicaltest02;

/**
 * Created by bordin on 5/20/16.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class CommunicationThread extends Thread {
    ServerThread serverThread;
    Socket socket;

    public CommunicationThread(ServerThread serverThread, Socket socket) {
        this.serverThread = serverThread;
        this.socket = socket;
    }


    @Override
    public void run() {

        try {
            PrintWriter printWriter = Utilities.getWriter(socket);
            TimeNow lasTimeNow = null;
            TimeNow timeNow = null;
            if (serverThread.data.containsKey(socket.getInetAddress().toString())) {
                lasTimeNow = serverThread.data.get(socket.getInetAddress().toString());
            }
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet HttpGet = new HttpGet("http://www.timeapi.org/utc/now/");
            HttpResponse httpGetResponse = httpClient.execute(HttpGet);
            HttpEntity httpGetEntity = httpGetResponse.getEntity();

            String currentTime = EntityUtils.toString(httpGetEntity);
            printWriter.println(currentTime);
            printWriter.flush();

            socket.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}