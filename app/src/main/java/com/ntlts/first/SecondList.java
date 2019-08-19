package com.ntlts.first;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_list_page);

        TextView textView = (TextView) findViewById(R.id.textView3);
        Intent intent = getIntent(); //Activity.getIntent() is not deprecated.
        int message = intent.getIntExtra("com.ntlts.first.EXTRA_NUMBER",0);
        textView.setText(String.valueOf(message));
    }
}