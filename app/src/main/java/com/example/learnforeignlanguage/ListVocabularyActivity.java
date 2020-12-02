package com.example.learnforeignlanguage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.GridView;

import com.example.learnforeignlanguage.adapter.AdapterVocabulary;
import com.example.learnforeignlanguage.dao.CustomDetailDao;
import com.example.learnforeignlanguage.dao.VocabularyDao;
import com.example.learnforeignlanguage.mode.CustomDetail;
import com.example.learnforeignlanguage.mode.Vocabulary;

import java.util.ArrayList;
import java.util.List;

public class ListVocabularyActivity extends AppCompatActivity {
    GridView gridViewVocabulary;
    SharedPreferences sharedPreferences;
    CustomDetailDao customDetailDao;
    VocabularyDao vocabularyDao;
    List<CustomDetail> listCustomDetail;
    List<Vocabulary> listVocabulary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_vocabulary);

        anhXa();

        start();
    }

    private void start() {
        listCustomDetail = customDetailDao.getAllCustomDetail();
        int id = sharedPreferences.getInt("idCustom",0);
        for (int i=0;i<listCustomDetail.size();i++){
            if(listCustomDetail.get(i).getIdCustom()==id) {
                List<Vocabulary> list = vocabularyDao.timKiem(listCustomDetail.get(i).getIdVocabulary());
                listVocabulary.add(list.get(0));
            }
        }
        AdapterVocabulary adapterVocabulary = new AdapterVocabulary(listVocabulary,this);
        gridViewVocabulary.setAdapter(adapterVocabulary);
    }

    private void anhXa() {
        gridViewVocabulary = findViewById(R.id.gridView_listVocabulary);
        sharedPreferences = getSharedPreferences("phong",MODE_PRIVATE);
        customDetailDao = new CustomDetailDao(this);
        vocabularyDao = new VocabularyDao(this);
        listCustomDetail = new ArrayList<>();
        listVocabulary = new ArrayList<>();
    }
}