package com.androidwavelength.asyncktask2;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnDownload;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupListeners();
    }

    private void setupListeners() {
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  String localPath = Util.download("https://bitcode.in/file1.zip");
                String[] fileUrls = {
                        "https://bitcode.in/file1",
                        "https://bitcode.in/file2",
                        "https://bitcode.in/file3"
                };

                DownloadThread downloadThread =
                        new DownloadThread(
                                new DownloadHandler()
                        );
                downloadThread.equals(fileUrls);
            }
        });
    }

    private void initViews() {
        btnDownload = findViewById(R.id.btnDownload);
    }

    private class DownloadHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            if(msg != null && msg.obj != null) {

                switch (msg.what) {
                    case 1:
                        mt((String)msg.obj);
                        showProgressDialog();
                        break;
                    case 2:
                        btnDownload.setText(((Integer)msg.obj) + "%");
                        progressDialog.setProgress((Integer)msg.obj);
                        break;
                    case 3:
                        mt(((Float)msg.obj) + " is final result...");
                        progressDialog.dismiss();
                        break;
                }
            }
        }
    }

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Downloading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
    }

    private void mt(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}