package com.example.nguyenlinh.firebaseprogramming;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gotoActiviy(View clickButton){
        int id=clickButton.getId();
        switch (id){
            case R.id.button:
                Intent intent=new Intent(this,StringValuesActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent1=new Intent(this,ObjectValuesActivity.class);
                startActivity(intent1);
                break;
            case R.id.button3:
                Intent intent2=new Intent(this,HashMapActivity.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }
}
