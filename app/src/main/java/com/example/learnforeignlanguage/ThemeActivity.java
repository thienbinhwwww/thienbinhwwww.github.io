package com.example.learnforeignlanguage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.example.learnforeignlanguage.DAO.ThemeDao;
import com.example.learnforeignlanguage.mode.Theme;

import java.util.ArrayList;
import java.util.List;

public class ThemeActivity extends AppCompatActivity {
    GridView gridView;
    List<Theme> listTheme = new ArrayList<>();
    ThemeDao themeDao;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        anhXa();
        start();



    }

    private void start() {
        listTheme = themeDao.getAllTheme();
        AdapterTheme adapterTheme = new AdapterTheme(listTheme,this);
        gridView.setAdapter(adapterTheme);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                editor.clear();
                editor.putInt("idCustom", listTheme.get(i).getIdCustom());
                editor.commit();

                finish();
                Intent intent = new Intent(ThemeActivity.this,ListGameActivity.class);
                startActivity(intent);
            }
        });
    }

    private void anhXa() {
        gridView= findViewById(R.id.gridView_theme);
        themeDao = new ThemeDao(this);
        sharedPreferences = getSharedPreferences("phong",MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
}