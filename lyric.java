package com.example.muzic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;


public class lyric extends AppCompatActivity {
    public Button tam,hin,tel,kan,urd;
    public EditText et;
    public TextView tv;
    public Translator translatorhindi,translatortelugu,translatortamil,translatorurdu,translatorkannada;
    public Boolean boolhindi=false,booltelugu=false,booltamil=false,boolurdu=false,boolkannada=false;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyric);
        Intent intent=getIntent();

        et=findViewById(R.id.et);
        tam=findViewById(R.id.tam);
        tel=findViewById(R.id.tel);
        hin=findViewById(R.id.hin);
        kan=findViewById(R.id.kan);
        urd=findViewById(R.id.urd);

        tv=findViewById(R.id.tv);
        TranslatorOptions translatorOptionshindi=new TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.ENGLISH)
                .setTargetLanguage(TranslateLanguage.HINDI)
                .build();
        translatorhindi= Translation.getClient(translatorOptionshindi);

        TranslatorOptions translatorOptionstelugu=new TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.ENGLISH)
                .setTargetLanguage(TranslateLanguage.TELUGU)
                .build();
        translatortelugu= Translation.getClient(translatorOptionstelugu);
        TranslatorOptions translatorOptionstamil=new TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.ENGLISH)
                .setTargetLanguage(TranslateLanguage.TAMIL)
                .build();
        translatortamil= Translation.getClient(translatorOptionstamil);

        TranslatorOptions translatorOptionskannada=new TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.ENGLISH)
                .setTargetLanguage(TranslateLanguage.KANNADA)
                .build();
        translatorkannada= Translation.getClient(translatorOptionskannada);

        TranslatorOptions translatorOptionsurdu=new TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.ENGLISH)
                .setTargetLanguage(TranslateLanguage.URDU)
                .build();
        translatorurdu= Translation.getClient(translatorOptionsurdu);
        downloadmodel();
    }

    public void downloadmodel()
    {
        DownloadConditions downloadConditions = new DownloadConditions.Builder()
                .requireWifi()
                .build();

        translatorhindi.downloadModelIfNeeded(downloadConditions)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        boolhindi=true;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        boolhindi=false;
                    }
                });
        translatortelugu.downloadModelIfNeeded(downloadConditions)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        booltelugu=true;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        booltelugu=false;
                    }
                });
        translatortamil.downloadModelIfNeeded(downloadConditions)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        booltamil=true;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        booltamil=false;
                    }
                });
        translatorkannada.downloadModelIfNeeded(downloadConditions)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        boolkannada=true;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        boolkannada=false;
                    }
                });
        translatorurdu.downloadModelIfNeeded(downloadConditions)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        boolurdu=true;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        boolurdu=false;
                        Toast.makeText(lyric.this, "downloading..", Toast.LENGTH_SHORT).show();
                    }

                });
    }

    public void opentamil(View v)
    {
        if(booltamil) {
            translatortamil.translate(et.getText().toString())
                    .addOnSuccessListener(new OnSuccessListener<String>() {
                        @Override
                        public void onSuccess(String s) {
                            tv.setText(s);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
        }
    }
    public void openhin(View v)
    {
        if(boolhindi) {
            translatorhindi.translate(et.getText().toString())
                    .addOnSuccessListener(new OnSuccessListener<String>() {
                        @Override
                        public void onSuccess(String s) {
                            tv.setText(s);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
        }
    }
    public void opentel(View v)
    {
        if(booltelugu) {
            translatortelugu.translate(et.getText().toString())
                    .addOnSuccessListener(new OnSuccessListener<String>() {
                        @Override
                        public void onSuccess(String s) {
                            tv.setText(s);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
        }
    }
    public void openkan(View v)
    {
        if(boolkannada) {
            translatorkannada.translate(et.getText().toString())
                    .addOnSuccessListener(new OnSuccessListener<String>() {
                        @Override
                        public void onSuccess(String s) {
                            tv.setText(s);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
        }
    }
    public void openurd(View v)
    {
        if(boolurdu) {
            translatorurdu.translate(et.getText().toString())
                    .addOnSuccessListener(new OnSuccessListener<String>() {
                        @Override
                        public void onSuccess(String s) {
                            tv.setText(s);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
        }
    }
}
