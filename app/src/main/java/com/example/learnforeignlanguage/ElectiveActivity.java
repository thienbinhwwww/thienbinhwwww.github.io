package com.example.learnforeignlanguage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.learnforeignlanguage.adapter.AdapterVocabulary;
import com.example.learnforeignlanguage.dao.CustomDao;
import com.example.learnforeignlanguage.dao.CustomDetailDao;
import com.example.learnforeignlanguage.dao.ThemeDao;
import com.example.learnforeignlanguage.dao.VocabularyDao;
import com.example.learnforeignlanguage.mode.Custom;
import com.example.learnforeignlanguage.mode.CustomDetail;
import com.example.learnforeignlanguage.mode.Theme;
import com.example.learnforeignlanguage.mode.Vocabulary;

import java.util.ArrayList;
import java.util.List;

public class ElectiveActivity extends AppCompatActivity {
    List<Custom> listCustom,listCustomC;
    List<CustomDetail> listCustomDetail;
    List<Vocabulary> listVocabulary;
    List<Theme> listTheme;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ThemeDao themeDao;
    CustomDao customDao;
    CustomDetailDao customDetailDao;
    VocabularyDao vocabularyDao;
    boolean c1;
    EditText tv_vocabulary,tv_n;
    int idCustom;
    GridView gridViewVocabulary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elective);

        anhXa();
    }

    private void anhXa() {
        listCustom = new ArrayList<>();
        listCustomC = new ArrayList<>();
        listCustomDetail = new ArrayList<>();
        listVocabulary = new ArrayList<>();
        listTheme = new ArrayList<>();

        tv_vocabulary = findViewById(R.id.edt_add);
        tv_n = findViewById(R.id.edt_add_m);

        customDao = new CustomDao(this);
        customDetailDao = new CustomDetailDao(this);
        vocabularyDao = new VocabularyDao(this);
        themeDao = new ThemeDao(this);
        c1 = true;
        sharedPreferences = getSharedPreferences("phong",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gridViewVocabulary=findViewById(R.id.gridView_newVocalody);
    }

    public void add(View view){
        idCustom=1;
        if(c1) {
            c1 = false;
            listCustom = customDao.getAllCustom();
            boolean ch;
            while (true){
                ch = true;
                for (int i=0;i < (listCustom.size());i++){
                    if(listCustom.get(i).getIdCustom()==idCustom){
                        ch = false;
                        break;
                    }
                }
                if(ch){
                    customDao.addCustom(new Custom(idCustom));
                    break;
                }
                idCustom++;
            }

            Theme theme = new Theme();
            theme.setIdCustom(idCustom);
            theme.setTheme("chủ đề tự do");
            themeDao.addTheme(theme);

            Vocabulary vocabulary = new Vocabulary();
            vocabulary.setVocabulary(tv_vocabulary.getText().toString());
            vocabulary.setMeans(tv_n.getText().toString());
            vocabularyDao.addVocabulary(vocabulary);

            int idVo=0;
            listVocabulary = vocabularyDao.getAllVocabulary();
            for (int i=0;i<listVocabulary.size();i++){
                if(tv_vocabulary.getText().toString().equals(listVocabulary.get(i).getVocabulary())){
                    idVo = listVocabulary.get(i).getIdVocabulary();
                }
            }

            CustomDetail customDetail= new CustomDetail();
            customDetail.setIdCustom(idCustom);
            customDetail.setIdVocabulary(idVo);
            customDetailDao.addCustomDetail(customDetail);

            Toast.makeText(this,idCustom+"-"+idVo,Toast.LENGTH_LONG).show();

            editor.clear();
            editor.putInt("idCustom", idCustom);
            editor.commit();

            addAdapter(idCustom);

        }else {
            Vocabulary vocabulary = new Vocabulary();
            vocabulary.setVocabulary(tv_vocabulary.getText().toString());
            vocabulary.setMeans(tv_n.getText().toString());
            vocabularyDao.addVocabulary(vocabulary);

            int idVo=0;
            listVocabulary = vocabularyDao.getAllVocabulary();
            for (int i=0;i<listVocabulary.size();i++){
                if(tv_vocabulary.getText().toString().equals(listVocabulary.get(i).getVocabulary())){
                    idVo = listVocabulary.get(i).getIdVocabulary();
                }
            }

            CustomDetail customDetail= new CustomDetail();
            customDetail.setIdCustom(idCustom);
            customDetail.setIdVocabulary(idVo);
            customDetailDao.addCustomDetail(customDetail);
            addAdapter(idCustom);
        }
    }
    public void strat(View view){
        if(!c1) {
            Intent intent = new Intent(ElectiveActivity.this, ListGameActivity.class);
            startActivity(intent);
        }
    }



    public void addAdapter(int id){
        listVocabulary.clear();
        listCustomDetail = customDetailDao.timKiem(idCustom);
        for (int i=0;i<listCustomDetail.size();i++){
            listVocabulary.add(vocabularyDao.timKiem(listCustomDetail.get(i).getIdVocabulary()).get(0));
        }
        AdapterVocabulary adapterVocabulary = new AdapterVocabulary(listVocabulary,this);
        gridViewVocabulary.setAdapter(adapterVocabulary);
    }
}