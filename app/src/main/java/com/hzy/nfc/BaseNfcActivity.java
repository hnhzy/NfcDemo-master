package com.hzy.nfc;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
* Description BaseNfcActivity
* @author hzy
* Create on 2019/4/29 11:29
*/
public class BaseNfcActivity extends AppCompatActivity {

    /**
     * nfc适配器对象
     */
    protected NfcAdapter mNfcAdapter;
    /**
     * 延迟Intent
     */
    protected PendingIntent mPendingIntent;
    /**
     * nfc标签对象
     */
    protected Tag mTag;

    //启动activity,界面可见时
    @Override
    protected void onStart() {
        super.onStart();
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mNfcAdapter == null) {//判断设备是否支持NFC功能
            Toast.makeText(this, "设备不支持NFC功能!", Toast.LENGTH_SHORT);
            finish();
            return;
        }
        if (!mNfcAdapter.isEnabled()) {//判断设备NFC功能是否打开
            Toast.makeText(this, "请到系统设置中打开NFC功能!", Toast.LENGTH_SHORT);
            finish();
            return;
        }
        //创建PendingIntent对象,当检测到一个Tag标签就会执行此Intent
        mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()), 0);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);//获取到Tag标签对象
        String[] techList = mTag.getTechList();
        System.out.println("标签支持的tachnology类型：");
        for (String tech : techList) {
            System.out.println(tech);
        }
    }

    //页面获取到焦点
    @Override
    protected void onResume() {
        super.onResume();
        if (mNfcAdapter != null) {
            //打开前台发布系统，使页面优于其它nfc处理.当检测到一个Tag标签就会执行mPendingItent
            mNfcAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
        }
    }

    //页面失去焦点
    @Override
    protected void onPause() {
        super.onPause();
        if (mNfcAdapter != null) {
            //关闭前台发布系统
            mNfcAdapter.disableForegroundDispatch(this);
        }
    }

}
