package com.example.ordersystem_client;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Hamburger extends AppCompatActivity {
    private String order1;
    public String meal = "";
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
                TextView ml = new TextView(this);
                ml.setText(menulist.split(",")[1] + "\t" + menulist.split(",")[2]);
                EditText editText01 = new EditText(this);
                LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
                ll.addView(ml);
                ll.addView(editText01);

            }
        }
    }
}
