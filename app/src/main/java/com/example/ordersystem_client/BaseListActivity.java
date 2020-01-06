package com.example.ordersystem_client;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

abstract class BaseListActivity extends AppCompatActivity {
    MyListAdapter adapter;
    RecyclerView rview;
    ArrayList<String> lTypes;
    ArrayList<String> lNames;
    ArrayList<String> lCosts;
    ArrayList<String> lNums;
    String type;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void Confirm(View v) {
    }
    public void Back(View v){
        onBackPressed();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
