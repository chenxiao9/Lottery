package com.chen.lottery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.apache.commons.codec.digest.DigestUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DigestUtils.md5Hex("aaa");
    }
}
