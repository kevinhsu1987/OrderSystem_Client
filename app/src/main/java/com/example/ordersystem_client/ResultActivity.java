package com.example.ordersystem_client;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.AsyncQueryHandler;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


public class ResultActivity extends BaseListActivity {
    MyResultAdapter adapter;
    int result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        setContentView(R.layout.activity_result);
        TextView textView = findViewById(R.id.tResult);
        rview = findViewById(R.id.recycleview);
        lTypes = bundle.getStringArrayList("types");
        lNames = bundle.getStringArrayList("names");
        lCosts = bundle.getStringArrayList("costs");
        lNums = bundle.getStringArrayList("nums");
        for (int i = 0; i < lCosts.size(); i++) {
            int cost = Integer.parseInt(lCosts.get(i));
            int nums = Integer.parseInt(lNums.get(i));
            result = result + cost * nums;
        }
        textView.setText(String.format("總計：%d", result));
        adapter = new MyResultAdapter(this, lTypes, lNames, lCosts, lNums);
        rview.setLayoutManager(new LinearLayoutManager(this));
        rview.setAdapter(adapter);
    }

    @Override
    public void Confirm(View v) {
        super.Confirm(v);
        int numTotal=0;
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < lCosts.size(); i++) {
            stringBuilder.append(lTypes.get(i)).append(",")
                    .append(lNames.get(i)).append(",")
                    .append(lCosts.get(i)).append(",")
                    .append(lNums.get(i)).append("/");
        }
        stringBuilder.append(result);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("確認").setMessage("是否確認送出？");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new MySocket().execute("192.168.0.141", "8000", stringBuilder.toString());
                    }          //TODO:SOCKET
                }
        );
        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}

class MySocket extends AsyncTask<String, Void, Void> {
    Socket socket;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(String... strings) {
        try {
            socket = new Socket(strings[0], Integer.parseInt(strings[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintStream writer = null;
        try {
            writer = new PrintStream(socket.getOutputStream());
            writer.println("Order!");
            writer.println(strings[2]);
            writer.flush();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
