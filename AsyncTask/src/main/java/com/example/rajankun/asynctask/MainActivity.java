package com.example.rajankun.asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * This is a just a basic implementation of Async Task in Android
 * This example also shows the pitfalls in using the android Async task.
 * If you rotate the screen while running the async task, then the  handlers
 * (elements in activity's UI like textView) is no longer valid because the old
 * activity which started async task is already destroyed and nothing happens in the new activity.
 */

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button button = (Button) findViewById(R.id.download_button);
        progressBar = (ProgressBar) findViewById(R.id.pbLoading);
        textView = (TextView) findViewById(R.id.appStatus);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDownload();
            }
        });
    }

    public void startDownload(){
        DownloadTask downloadTask = new DownloadTask();
        downloadTask.execute(3000);
    }

    public class DownloadTask extends AsyncTask<Integer, Void, Boolean>{

        @Override
        protected void onPreExecute() {
            // Runs on the UI thread before doInBackground
            // Good for toggling visibility of a progress indicator
            progressBar.setVisibility(ProgressBar.VISIBLE);
            textView.setText("Download started ...");
        }

        @Override
        protected Boolean doInBackground(Integer... params) {
            Integer sleepTime = params[0];
            //simulating some complex task
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result){
            if(result)
                textView.setText("Downloaded !!");

            progressBar.setVisibility(ProgressBar.INVISIBLE);
        }

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
