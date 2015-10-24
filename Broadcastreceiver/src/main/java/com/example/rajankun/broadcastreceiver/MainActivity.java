package com.example.rajankun.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

/** Example showing android broadcast receiver. **/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button sendBroadcast = (Button) findViewById(R.id.send_broadcast_btn);

        /** when clicked on this button it sends a broadcast which is received
         * by a broadcast receiver. The MyReceiver class extends from BroadcastReceiver
         * and registers itself in AndroidManifest.xml file. So when we sendBroadcast(intent)
         * the onReceive() method of MyReceiver class receives the intent.
         */
        sendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.example.rajankun.broadcastreceiver");
                sendBroadcast(intent);
            }
        });

        final Button sendLocal = (Button) findViewById(R.id.send_broadcast_local_btn);

        /** Anonymous class extends BroadcastReceiver **/
        BroadcastReceiver localMsg = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String msg = intent.getStringExtra("message");
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        };

        /** Registering the local anonymous broadcast receiver **/
        LocalBroadcastManager.getInstance(this).registerReceiver(localMsg,
                new IntentFilter("my-event"));


        /** send a local event which will be captured by anonymous receiver **/
        sendLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("my-event");
                // add data
                intent.putExtra("message", "Local Message !!");
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
