package ro.pub.cs.systems.eim.practicaltest02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest02MainActivity extends AppCompatActivity {

    // Server widgets
    private EditText serverPortEditText = null;
    private Button connectButton = null;

    // Client widgets
    private EditText clientAddressEditText = null;
    private EditText clientPortEditText = null;
    private Button clientGetUtcButton = null;
    private TextView timeUtcNow = null;

    private ServerThread serverThread = null;
    private ClientThread clientThread = null;

    private ConnectButtonClickListener connectButtonClickListener = new ConnectButtonClickListener();

    private class ConnectButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            String serverPort = serverPortEditText.getText().toString();
            if (serverPort == null || serverPort.isEmpty()) {
                Toast.makeText(
                        getApplicationContext(),
                        "Server port missing!",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            serverThread = new ServerThread(Integer.parseInt(serverPort.toString()));

            if (serverThread.getServerSocket() != null) {
                serverThread.start();
            } else {
                Log.e("[practicaltest02]", "[MAIN ACTIVITY] Could not creat server thread!");
            }
        }
    }

    private GetWeatherForecastButtonClickListener getWeatherForecastButtonClickListener = new GetWeatherForecastButtonClickListener();

    private class GetWeatherForecastButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            String serverAddress = clientAddressEditText.getText().toString();
            String serverPort = clientPortEditText.getText().toString();

            if (serverAddress == null || serverAddress.isEmpty() ||
                    serverPort == null || serverPort.isEmpty()) {
                Toast.makeText(
                        getApplicationContext(),
                        "Client port/address missing!",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            if (serverThread == null || !serverThread.isAlive()) {
                Toast.makeText(
                        getApplicationContext(),
                        "Client's server missing!",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            clientThread = new ClientThread(serverAddress, serverPort, timeUtcNow);

            if (!clientThread.isAlive()) {
                clientThread.start();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test02_main);

        serverPortEditText = (EditText) findViewById(R.id.server_port);
        connectButton = (Button) findViewById(R.id.Start_server);
        clientAddressEditText = (EditText) findViewById(R.id.clientAddr);
        clientPortEditText = (EditText) findViewById(R.id.clientPort);
        clientGetUtcButton = (Button) findViewById(R.id.clientGetUtcButton);
        timeUtcNow = (TextView) findViewById(R.id.timeUtcNow);
        timeUtcNow .setText("");
        connectButton.setOnClickListener(connectButtonClickListener);
        clientGetUtcButton.setOnClickListener(getWeatherForecastButtonClickListener);

    }
}