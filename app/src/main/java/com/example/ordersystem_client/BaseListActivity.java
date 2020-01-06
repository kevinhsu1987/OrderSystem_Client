package com.example.ordersystem_client;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

abstract class BaseListActivity extends AppCompatActivity {
    MyAdapter adapter;
    RecyclerView rview;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        String type = bundle.getString("type");
        int layout = bundle.getInt("layout");
        setContentView(layout);
        rview = findViewById(R.id.recycleview);
        ArrayList<String> lNames = new ArrayList<>();
        ArrayList<String> lCosts = new ArrayList<>();
        String order1 = getSharedPreferences("menu", 0).getString("menu", null);
        if(order1 != null) {
            String[] menulists = order1.split("/");
            ArrayList<String> name = new ArrayList<>();
            for (String menulist : menulists) {
                String[] temp = menulist.split(",");
                if (temp[0].equals(type)) {
                    lNames.add(temp[1]);
                    lCosts.add(temp[2]);
                }
            }
        }
        adapter = new MyAdapter(this, lNames, lCosts);
        rview.setLayoutManager(new LinearLayoutManager(this));
        rview.setAdapter(adapter);
        System.out.println(order1);
    }
}
