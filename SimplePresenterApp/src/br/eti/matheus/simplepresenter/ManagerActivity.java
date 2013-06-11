package br.eti.matheus.simplepresenter;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

public class ManagerActivity extends Activity {
	
	private TextView lblLog;
	
	private SimplePresenterClient spClient;
	private SlideManager slideManager;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager);
        
        String simplePresenterServerIP = "";
        
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
        	simplePresenterServerIP = extras.getString("server_ip");
        }
        
        spClient = new SimplePresenterClient(simplePresenterServerIP);
        slideManager = new SlideManager(spClient);
        
        lblLog = (TextView)findViewById(R.id.lbl_log);
        lblLog.setText("Connected at" + simplePresenterServerIP);
    }
    
    @Override
    public boolean dispatchKeyEvent(KeyEvent event){
    	int action = event.getAction();
    	int keyCode = event.getKeyCode();
    	switch (keyCode) {
        case KeyEvent.KEYCODE_VOLUME_UP:
        	if (action == KeyEvent.ACTION_UP){
        		slideManager.nextSlide();
        		lblLog.setText("Next");
        	}
        	return true;
    	case KeyEvent.KEYCODE_VOLUME_DOWN:
        	if (action == KeyEvent.ACTION_DOWN){
        		slideManager.prevSlide();
        		lblLog.setText("Prev");
        	}
        	return true;
    	}
        return super.dispatchKeyEvent(event);
    }

}