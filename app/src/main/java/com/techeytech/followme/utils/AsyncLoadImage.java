package com.techeytech.followme.utils;

import android.os.AsyncTask;
import android.util.Log;

import com.techeytech.followme.interfaces.OnTaskCompleted;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class AsyncLoadImage extends AsyncTask<String, String, String> {
    private String resp;
    private OnTaskCompleted listener;
    private int position;

    public AsyncLoadImage(OnTaskCompleted listener) {
        this.listener = listener;
    }
    public AsyncLoadImage(OnTaskCompleted listener, int position) {
        this.listener = listener;
        this.position=position;
    }
    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL(params[0]); //you can write here any link
            URLConnection uCon = url.openConnection();
            InputStream is = uCon.getInputStream();

            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayBuffer baf = new ByteArrayBuffer(50);
            int current = 0;
            while ((current = bis.read()) != -1) {
                baf.append((byte) current);
            }
            UtilMethods.createDirectory(baf, params[1]);

        } catch (IOException e) {
            Log.d("ImageManager", "Error: " + e);
        }
        return resp;
    }


    @Override
    protected void onPostExecute(String result) {
        listener.onTaskCompleted(position);
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onProgressUpdate(String... text) {

    }
}