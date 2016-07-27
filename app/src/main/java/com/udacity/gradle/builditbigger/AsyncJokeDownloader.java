package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.ayush.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * Created by abmitra on 8/2/2015.
 */
public class AsyncJokeDownloader {
    private DownloadListener downloadListener;
    private static MyApi myApiService = null;
    public AsyncJokeDownloader( DownloadListener downloadListener )
    {
        this.downloadListener = downloadListener;
    }

    public void downloadJoke()
    {
        new EndpointsAsyncTask().execute();
    }
    class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            if(myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl("https://builditbigger-1025.appspot.com/_ah/api/");
                // end options for devappserver

                myApiService = builder.build();
            }

            try {
                return myApiService.sayHi().execute().getData();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            downloadListener.downloadCompleted(result);
        }
    }

}