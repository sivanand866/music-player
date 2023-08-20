package com.example.muzic;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

public class sp extends AppCompatActivity {
    public TextView sptv;
    public EditText spet;
    public Button save;
    public String k;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp);
        Intent intent=getIntent();
        sptv=findViewById(R.id.sptv);
        spet=findViewById(R.id.spet);
        save=findViewById(R.id.save);
        k=intent.getStringExtra("na");
        SharedPreferences spf=getSharedPreferences("mypref",MODE_PRIVATE);
        SharedPreferences.Editor ed=spf.edit();
        ed.apply();
        String saved=spf.getString(k,"enter lyric");
        sptv.setText(saved);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=spet.getText().toString();
                ed.putString(k,s);
                ed.apply();
                sptv.setText(s);
            }
        });

    }
}
