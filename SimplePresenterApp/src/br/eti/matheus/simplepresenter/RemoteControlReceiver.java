package br.eti.matheus.simplepresenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;

public class RemoteControlReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
    	System.out.println(intent.getAction());
        if (Intent.ACTION_MEDIA_BUTTON.equals(intent.getAction())) {
            KeyEvent event = (KeyEvent)intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
            if (KeyEvent.KEYCODE_VOLUME_UP == event.getKeyCode()) {
                Log.d("Volume UP", "pressionado volume up");
            }
            if (KeyEvent.KEYCODE_VOLUME_DOWN == event.getKeyCode()) {
            	Log.d("Volume DOWN", "pressionado volume down");
            }
        }
    }
}