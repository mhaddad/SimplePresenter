package br.eti.matheus.simplepresenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SimplePresenterAppActivity extends Activity {
	
	private TextView lblLog;
	private EditText txtIP;
	
	private SimplePresenterClient spClient;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        txtIP = (EditText)findViewById(R.id.txtIP);
        txtIP.setText("192.168.1.20");
    }
    
    public void connectToSimplePresenterServer(View view){
    	spClient = new SimplePresenterClient(txtIP.getText().toString());
    	spClient.connect();
    	if (spClient.getConnected()){
    		Intent intentManager = new Intent(view.getContext(), ManagerActivity.class);
    		intentManager.putExtra("server_ip", txtIP.getText().toString());
    		startActivityForResult(intentManager, 0);
    	}
    	else
    		lblLog.setText("N‹o conectado!");
    }

}