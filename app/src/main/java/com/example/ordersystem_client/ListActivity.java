package com.example.ordersystem_client;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;


public class ListActivity extends BaseListActivity {
    ArrayList<String> names;
    ArrayList<String> nums;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        type = bundle.getString("type");
        names = bundle.getStringArrayList("names");
        nums = bundle.getStringArrayList("nums");
        int layout = bundle.getInt("layout");
        setContentView(layout);
        rview = findViewById(R.id.recycleview);
        lNames = new ArrayList<>();
        lCosts = new ArrayList<>();
        lNums = new ArrayList<>();
        String order1 = getSharedPreferences("menu", 0).getString("menu", null);
        if (order1 != null) {
            String[] menulists = order1.split("/");
            for (String menulist : menulists) {
                String[] temp = menulist.split(",");
                if (temp[0].equals(type)) {
                    lNames.add(temp[1]);
                    lCosts.add(temp[2]);
                }
            }

        }
        if(names !=null) {
            for (int i=0;i<lNames.size();i++)
                if (names.indexOf(lNames.get(i)) >= 0)
                    lNums.add(nums.get(names.indexOf(lNames.get(i))));
                else
                    lNums.add("");
        }


        adapter = new MyListAdapter(this, lNames, lCosts, lNums);
        rview.setLayoutManager(new LinearLayoutManager(this));
        rview.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
//        for(String n:names){
//            int index = lNames.indexOf(n);
//            adapter.setNumsByPosition(Integer.parseInt(nums.get(names.indexOf(n))),index);
//        }
    }

    @Override
    public void Confirm(View v) {
        super.Confirm(v);
        String result = "";
        for (int i = 0; i < lNames.size(); i++) {
            View view = rview.getLayoutManager().findViewByPosition(i);
            EditText t = view.findViewById(R.id.editText);
            String s = t.getText().toString();
            if (!s.equals("")) {
                TextView v1 = view.findViewById(R.id.tName);
                TextView v2 = view.findViewById(R.id.tCost);
                result = result + type + "," +
                        v1.getText().toString() + "," +
                        v2.getText().toString() + "," +
                        Integer.parseInt(s) + "/";
            }
            //Integer.parseInt(t.getText().toString())
        }
        Bundle bundle = new Bundle();
        bundle.putString("result", result);
        Intent intent = new Intent(ListActivity.this, MainActivity.class);
        intent.putExtras(bundle);
        //intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        ListActivity.this.setResult(RESULT_OK, intent);
        finish();
        //onBackPressed();
    }
}
