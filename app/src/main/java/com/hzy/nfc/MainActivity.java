package com.hzy.nfc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
* Description MainActivity
* @author hzy
* Create on 2019/4/29 11:30
*/
public class MainActivity extends BaseNfcActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button://读写NDEF格式
                startActivity(new Intent(this,NdefActivity.class));
                break;
            case R.id.button2://读写MifareClassic格式
                startActivity(new Intent(this,MifareClassicActivity.class));
                break;
        }
    }
}
