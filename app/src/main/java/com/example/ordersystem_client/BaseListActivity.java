package com.example.ordersystem_client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

abstract class BaseListActivity extends AppCompatActivity {
    MyAdapter adapter;
    RecyclerView rview;
    ArrayList<String> lNames;
    ArrayList<String> lCosts;
    String type;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        type = bundle.getString("type");
        int layout = bundle.getInt("layout");
        setContentView(layout);
        rview = findViewById(R.id.recycleview);
        lNames = new ArrayList<>();
        lCosts = new ArrayList<>();
        String order1 = getSharedPreferences("menu", 0).getString("menu", null);
        if (order1 != null) {
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
    }

    public void Confirm(View v) {
        String result = "";
        for (int i = 0; i < lNames.size(); i++) {
            View view = rview.getLayoutManager().findViewByPosition(i);
            EditText t = view.findViewById(R.id.editText);
            String s = t.getText().toString();
            if (!s.equals("")) {
                TextView v1 = view.findViewById(R.id.tName);
                TextView v2 = view.findViewById(R.id.tCost);
                result = result + type + "," +
                    v1.getText().toString()+ "," +
                    v2.getText().toString()+ "," +
                    Integer.parseInt(s) + "/";
            }
            //Integer.parseInt(t.getText().toString())
        }
        Bundle bundle = new Bundle();
        bundle.putString("result",result);
        Intent intent = new Intent(BaseListActivity.this, MainActivity.class);
        intent.putExtras(bundle);
        //intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        BaseListActivity.this.setResult(RESULT_OK, intent);
        finish();
        //onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
