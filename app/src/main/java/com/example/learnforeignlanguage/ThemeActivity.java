package com.example.learnforeignlanguage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        anhXa();

        themeDao = new ThemeDao(this);

        listTheme = themeDao.getAllTheme();


        AdapterTheme adapterTheme = new AdapterTheme(listTheme,this);
        gridView.setAdapter(adapterTheme);

    }

    private void anhXa() {
        gridView= findViewById(R.id.gridView_theme);
    }
}