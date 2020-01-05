package com.example.ordersystem_client;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public String menu(){
        ClientThreadCode menu = new ClientThreadCode("192.168.0.12", 8000);
        String menu1 = menu.data;
        return menu1;
    }


}
