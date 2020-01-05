package com.example.ordersystem_client;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Hamburger extends AppCompatActivity {
    private String order1;
    private int i;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hamburger);
        MainActivity order = new MainActivity();
        order1 = order.menu();
        String[] menulists = order1.split ("/");
        for(String menulist : menulists){
            String[] type = menulist.split(",");
            if (menulist.split(",")[0]=="美式漢堡"){

            }
        }
    }
}
