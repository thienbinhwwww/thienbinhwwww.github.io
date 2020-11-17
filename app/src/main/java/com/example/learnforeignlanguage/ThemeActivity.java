package com.example.learnforeignlanguage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.learnforeignlanguage.DAO.ThemeDao;
import com.example.learnforeignlanguage.mode.Theme;

import java.util.ArrayList;
import java.util.List;

public class ThemeActivity extends AppCompatActivity {
    ListView lv;
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
        lv.setAdapter(adapterTheme);

    }

    private void anhXa() {
        lv= findViewById(R.id.listView_theme);
    }
}