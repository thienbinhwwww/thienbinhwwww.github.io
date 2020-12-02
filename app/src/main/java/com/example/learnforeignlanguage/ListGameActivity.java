package com.example.learnforeignlanguage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ListGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_game);
    }

    public void gameI(View view){
        finish();
        Intent intent = new Intent(ListGameActivity.this,GameIActivity.class);
        startActivity(intent);
    }

    public void gameII(View view){
        finish();
        Intent intent = new Intent(ListGameActivity.this,GameIIActivity.class);
        startActivity(intent);
    }

    public void gameIII(View view){
        finish();
        Intent intent = new Intent(ListGameActivity.this,ListVocabularyActivity.class);
        startActivity(intent);
    }
}