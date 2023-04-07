package com.androidwavelength.asyncktask2;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

public class DownloadThread extends AsyncTask<String, Integer, Float> {

    private Handler handler;

    public DownloadThread(Handler handler) {
        this.handler = handler;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        Message message = new Message();
        message.what = 1;
        message.obj = "Downloading started";
        handler.sendMessage(message);
    }

    @Override
    protected Float doInBackground(String [] input) {

        for (String url : input) {

            for (int i = 0; i <=100; i++) {

                Integer[] progress =  new Integer[1];
                progress[0] = i;
                publishProgress(progress);

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return 12.12f;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        Message message = new Message();
        message.obj = values[0];
        message.what = 2;

        //handler.handleMessage(message);
        handler.sendMessage(message);
    }
}
