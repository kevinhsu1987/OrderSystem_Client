package com.example.ordersystem_client;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String data;
    SharedPreferences pref;
    ClientThreadCode clientThread;
    ArrayList<String> types;
    ArrayList<String> names;
    ArrayList<String> costs;
    ArrayList<String> nums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, Manifest.permission.ACCESS_NETWORK_STATE,1);
        }*/
        types = new ArrayList<>();
        names = new ArrayList<>();
        costs = new ArrayList<>();
        nums = new ArrayList<>();

        setContentView(R.layout.activity_main);
        clientThread = new ClientThreadCode("192.168.0.141", 8000);
        clientThread.start();
        pref = getSharedPreferences("menu", MODE_PRIVATE);

        data = clientThread.getData();
        String v = pref.getString("menu", null);
        if (v == null && data != null) {
            pref.edit().putString("menu", data).commit();
        } else {
            System.out.println(String.format("%s\r\n%s", v, data));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode >= 0) return;
        String s = data.getExtras().getString("result", "empty");
        System.out.println(s);
        String[] a = s.split("/");
        for (String mls : a) {
            String[] meals = mls.split(",");
            int typesPos = types.indexOf(meals[0]);
            int namesPos = names.indexOf(meals[1]);
            if (typesPos >= 0 && namesPos >= 0) {//兩名稱皆存於陣列中
                if (typesPos == namesPos) { //指向同一筆資料
                    types.remove(typesPos);
                    names.remove(typesPos);
                    costs.remove(typesPos);
                    nums.remove(typesPos);
                } else { //指向不同筆資料
                    if (types.get(namesPos).equals(meals[0])) {//位於namePos之types也等於meals[0]
                        types.remove(namesPos);
                        names.remove(namesPos);
                        costs.remove(namesPos);
                        nums.remove(namesPos);
                    } else if (names.get(typesPos).equals(meals[1])) {//位於typePos之name也等於meals[1]
                        types.remove(typesPos);
                        names.remove(typesPos);
                        costs.remove(typesPos);
                        nums.remove(typesPos);
                    } else {//確實不同資料
                        break;
                    }
                }
            }
            types.add(meals[0]);
            names.add(meals[1]);
            costs.add(meals[2]);
            nums.add(meals[3]);
        }
    }

    public void onClick(View v) {
        String type = "";
        int layout = R.layout.base_list_layout;
        Bundle bundle = new Bundle();
        bundle.putInt("layout", layout);
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.hamburger:
                type = "美式漢堡";
                break;
            case R.id.Bagel:
                type = "紐約貝果";
                break;
            case R.id.kungfu:
                type = "手做功夫堡";
                break;
            case R.id.Panini:
                type = "帕尼尼";
                break;
            case R.id.Croissant:
                type = "英式可頌";
                break;
            case R.id.sandwich:
                type = "古巴三明治";
                break;
            case R.id.submarine:
                type = "潛艇堡";
                break;
            case R.id.riceburger:
                type = "米漢堡";
                break;
            case R.id.burrito:
                type = "捲餅";
                break;
            case R.id.toast:
                type = "吐司";
                break;
            case R.id.lightmeal:
                type = "輕食";
                break;
            case R.id.eggwrap:
                type = "全麥蛋餅";
                break;
            case R.id.drink:
                type = "飲料";
                break;
            case R.id.coffee:
                type = "研磨咖啡";
                break;
            case R.id.order:
                type = "確認";
                bundle.putStringArrayList("types", types);
                bundle.putStringArrayList("names", names);
                bundle.putStringArrayList("costs", costs);
                bundle.putStringArrayList("nums", nums);
                break;
        }
        ArrayList<String> s = new ArrayList<>();
        ArrayList<String> n = new ArrayList<>();
        if(types.contains(type)){
            for(int i=0;i<types.size();i++){
                if(types.get(i).equals(type)) {
                    s.add(names.get(i));
                    n.add(nums.get(i));
                }
            }
            bundle.putStringArrayList("names", s);
            bundle.putStringArrayList("nums", n);
        }
        bundle.putString("type", type);

        intent.putExtras(bundle);
        if (type.equals("確認"))
            intent.setClass(MainActivity.this, ResultActivity.class);
        else
            intent.setClass(MainActivity.this, ListActivity.class);
        startActivityForResult(intent, 0);
    }
    private long firstTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long secondTime = System.currentTimeMillis();

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (secondTime - firstTime < 2000) {
                System.exit(0);
            } else {
                Toast.makeText(getApplicationContext(), "再按一次返回键退出", Toast.LENGTH_SHORT).show();
                firstTime = System.currentTimeMillis();
            }

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
