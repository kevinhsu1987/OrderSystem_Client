package com.example.ordersystem_client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    String data;
    SharedPreferences pref;
    ClientThreadCode clientThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, Manifest.permission.ACCESS_NETWORK_STATE,1);
        }*/

        setContentView(R.layout.activity_main);
        clientThread = new ClientThreadCode("192.168.0.141", 8000);
        clientThread.start();
        pref = getSharedPreferences("menu", MODE_PRIVATE);

    }

    @Override
    protected void onStart() {
        super.onStart();
        data = clientThread.getData();
        String v = pref.getString("menu", null);
        if (v == null && data != null) {
            pref.edit().putString("menu", data).commit();
        } else {
            System.out.println(String.format("%s\r\n%s", v, data));
        }
    }

    public void BtnHamburger(View v) {
        Bundle bundle = new Bundle();
        bundle.putString("type", "美式漢堡");
        bundle.putInt("layout", R.layout.base_list_layout);
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onClick(View v) {
        String type = null;
        int layout = R.layout.base_list_layout;
        Bundle bundle = new Bundle();
        bundle.putInt("layout", layout);
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.hamburger:type = "美式漢堡";break;
            case R.id.Bagel:type = "紐約貝果";break;
            case R.id.kungfu:type = "手做功夫堡";break;
            case R.id.Panini:type = "帕尼尼";break;
            case R.id.Croissant:type = "英式可頌";break;
            case R.id.sandwich:type = "古巴三明治";break;
            case R.id.submarine:type = "潛艇堡";break;
            case R.id.riceburger:type = "米漢堡";break;
            case R.id.burrito:type = "捲餅";break;
            case R.id.toast:type = "吐司";break;
            case R.id.lightmeal:type = "輕食";break;
            case R.id.eggwrap:type = "全麥蛋餅";break;
            case R.id.drink:type = "飲料";break;
            case R.id.coffee:type = "研磨咖啡";break;
        }
        bundle.putString("type",type);
        intent.putExtras(bundle);
        intent.setClass(MainActivity.this, ListActivity.class);
        startActivity(intent);
    }
}
