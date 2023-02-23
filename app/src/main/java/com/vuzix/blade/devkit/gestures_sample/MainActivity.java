package com.vuzix.blade.devkit.gestures_sample;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.Toast;

import com.vuzix.hud.actionmenu.ActionMenuActivity;

/**
 * Main Activity that extend ActionMenuActivity.
 * This main class provide the basic information monitoring and overriding Gestures and keyboard events.
 * For more information please reference:
 * https://developer.android.com/training/keyboard-input/commands
 * Used Android API Classes:
 * https://developer.android.com/reference/android/view/KeyEvent
 * https://developer.android.com/reference/android/view/MotionEvent
 */
public class MainActivity extends ActionMenuActivity {

    private final String TAG = "VuzixBDK-Gesture_Sample";
    private EditText logArea;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        logArea = findViewById(R.id.logArea);
    }

    /*
        Utilize this method to get keyDown events that the application can override.
        The Keycode can be used in a switch case statement to identify the required and desire events.
        https://developer.android.com/training/keyboard-input/commands
        https://developer.android.com/guide/topics/media-apps/mediabuttons
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // You always want to return the super event to have the system actually handle the event the
        // way they want. This is just for showing the available events.
//        return super.onKeyDown(keyCode, event);

        Log.d(TAG, "Key Code: " + String.valueOf(keyCode) );
        Log.d(TAG,"Key Event: " + event.toString());

        if(event.getAction() == KeyEvent.ACTION_DOWN)
        {
            logArea.setText("Key Code: " + String.valueOf(keyCode));
            logArea.append("\n Key Event: " + event.toString());
            showToast("Key Code: " + String.valueOf(keyCode) +
                    " \n Shortcut Key Event: " + event.toString());
        }


        return false;
    }

    /*
        This are all Motion events like Gestures.
     */
    @Override
    public boolean dispatchGenericMotionEvent(MotionEvent ev) {
        // You always want to return the super event to have the system actually handle the event the
        // way they want. This is just for showing the available events.
//        return super.dispatchGenericMotionEvent(ev);

        Log.d(TAG,"Generic motion Event: " + ev.toString());

//        logArea.append("\n Generic motion Event: " + ev.toString());

        return false;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        Log.d(TAG,"DispatchKey Event: " + event.toString());

        if (event.getAction() == KeyEvent.ACTION_DOWN){
            logArea.append("\n DispatchKey Event: " + event.toString());
            showToast("DispatchKey Event: " + event.toString());
        }



        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // You always want to return the super event to have the system actually handle the event the
        // way they want. This is just for showing the available events.
//        return super.dispatchTouchEvent(ev);


        Log.d(TAG,"Touch Event: " + ev.toString());

//        logArea.append("\n Touch Event: " + ev.toString());

        return false;
    }

    @Override
    public boolean dispatchTrackballEvent(MotionEvent ev) {
        // You always want to return the super event to have the system actually handle the event the
        // way they want. This is just for showing the available events.
//        return super.dispatchTrackballEvent(ev);

        Log.d(TAG,"Trackball Event: " + ev.toString());

//        logArea.append("\n Track Event: " + ev.toString());
        return false;
    }

    @Override
    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
        // You always want to return the super event to have the system actually handle the event the
        // way they want. This is just for showing the available events.
//        return super.dispatchKeyShortcutEvent(event);

        Log.d(TAG,"Shortcut Key Event: " + event.toString());

        logArea.append("\n Shortcut Key Event: " + event.toString());
        showToast("Shortcut Key Event: " + event.toString());

        return false;
    }

    /**
     * UI Helper Function to show a toast for dynamic content.
     * @param msg Message to display
     */
    private void showToast(String msg) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
        mToast.show();
    }

}
