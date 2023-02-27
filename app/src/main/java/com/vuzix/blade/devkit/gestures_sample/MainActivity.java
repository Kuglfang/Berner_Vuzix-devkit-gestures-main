package com.vuzix.blade.devkit.gestures_sample;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.Toast;

import com.vuzix.blade.devkit.gestures_sample.databinding.ActivityMainBinding;
import com.vuzix.hud.actionmenu.ActionMenuActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    private ActivityMainBinding binding; //faster than by id
    private final String TAG = "VuzixBDK-Gesture_Sample";
    private EditText logArea;
    private Toast mToast;

    Date datestartV1;//V1
    Date datestopV1;//V1

    boolean firsttime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater()); //faster than by id
        setContentView(binding.getRoot()); //faster than by id


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

        /*Log.d(TAG, "Key Code: " + String.valueOf(keyCode) ); Von mir auskomentiert
        Log.d(TAG,"Key Event: " + event.toString());

        if(event.getAction() == KeyEvent.ACTION_DOWN)
        {
            logArea.setText("Key Code: " + String.valueOf(keyCode));
            logArea.append("\n Key Event: " + event.toString());
            showToast("Key Code: " + String.valueOf(keyCode) +
                    " \n Shortcut Key Event: " + event.toString());
        }*/


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

        if(event.getAction() == KeyEvent.ACTION_DOWN)
        {
            if(firsttime)
            {
                datestartV1 = Calendar.getInstance().getTime();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                String dateTime = simpleDateFormat.format(datestartV1).toString();
                binding.starttime.setText(dateTime);

                binding.stoptime.setText("Stop Time");
                binding.time.setText("0h 0m 0s");
                firsttime = false;
            }
            else
            {
                datestopV1 = Calendar.getInstance().getTime();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                String dateTime = simpleDateFormat.format(datestopV1).toString();
                binding.stoptime.setText(dateTime);

                int diffhour = datestopV1.getHours() - datestartV1.getHours();
                int diffmin = datestopV1.getMinutes() - datestartV1.getMinutes();
                int diffsec = (datestopV1.getSeconds() - datestartV1.getSeconds());

                if (datestopV1.getSeconds() - datestartV1.getSeconds() < 0)
                {
                    diffmin -= 1;
                    diffsec = 60 - (diffsec * -1);
                }

                if (datestopV1.getMinutes() - datestartV1.getMinutes() < 0)
                {
                    diffhour -=1;
                    diffmin = 60 - (diffmin * -1);
                }

                binding.time.setText(diffhour + "h " + diffmin + "m " + diffsec + "s");

                firsttime = true;
            }
        }


        return super.dispatchKeyEvent(event);
    }




    /*Log.d(TAG,"DispatchKey Event: " + event.toString());

        if (event.getAction() == KeyEvent.ACTION_DOWN){
            logArea.append("\n DispatchKey Event: " + event.toString());
            showToast("DispatchKey Event: " + event.toString());
        }*/







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
