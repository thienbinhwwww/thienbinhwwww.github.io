package com.example.learnforeignlanguage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnforeignlanguage.DAO.CustomDetailDao;
import com.example.learnforeignlanguage.DAO.VocabularyDao;
import com.example.learnforeignlanguage.mode.CustomDetail;
import com.example.learnforeignlanguage.mode.Vocabulary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameIActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    CustomDetailDao customDetailDao;
    VocabularyDao vocabularyDao;
    List<CustomDetail> listCustomDetail;
    List<Vocabulary> listVocabulary;
    List<String> listMeans = new ArrayList<>();
    TextView tv_Vocabulary,tv_Means,tv_stt;
    ConstraintLayout layout_tich,layout_x;
    int poit=0;
    int temp=0;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_i);
        
        anhXa();
        
        start();
    }

    private void start() {
        int id = sharedPreferences.getInt("idCustom",0);
        listCustomDetail = customDetailDao.timKiem(id);
        for (int i=0;i<listCustomDetail.size();i++){
            listVocabulary.add(vocabularyDao.timKiem(listCustomDetail.get(i).getIdVocabulary()).get(0));
        }
        for (int i=0;i<listVocabulary.size();i++){
            listMeans.add(listVocabulary.get(i).getMeans());
        }
        createGame();
    }

    private void createGame() {
        if(temp>=10){
            Toast.makeText(GameIActivity.this,String.valueOf(poit),Toast.LENGTH_LONG).show();
            Intent intent = new Intent(GameIActivity.this,MenuActivity.class);
            startActivity(intent);
        }else {
            if (ramdom(temp)) {
                layout_tich.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        poit++;
                        temp++;
                        createGame();
                    }
                });
                layout_x.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        temp++;
                        createGame();
                    }
                });
            } else {

                layout_tich.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        temp++;
                        createGame();
                    }
                });
                layout_x.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        poit++;
                        temp++;
                        createGame();
                    }
                });
            }
        }
    }

    public boolean ramdom(int i) {
        int id = random.nextInt(listVocabulary.size());
        tv_stt.setText((i + 1) + "/10");
        int kt = random.nextInt(2);

        if (kt==0) {
            tv_Vocabulary.setText(listVocabulary.get(id).getVocabulary());
            tv_Means.setText(listVocabulary.get(id).getMeans());
            return true;
        } else {
            int ii = random.nextInt(listMeans.size());
            tv_Vocabulary.setText(listVocabulary.get(id).getVocabulary());
            tv_Means.setText(listMeans.get(ii));
            return false;
        }
    }


    private void anhXa() {
        tv_stt = findViewById(R.id.tv_gameI_stt);
        tv_Vocabulary = findViewById(R.id.tv_gameI_Vocabulary);
        tv_Means = findViewById(R.id.tv_gameI_means);
        layout_tich = findViewById(R.id.conLayout_gameI_tich);
        layout_x = findViewById(R.id.conLayout_gameI_x);
        customDetailDao = new CustomDetailDao(this);
        vocabularyDao = new VocabularyDao(this);
        listCustomDetail = new ArrayList<>();
        listVocabulary = new ArrayList<>();
        sharedPreferences =getSharedPreferences("phong",MODE_PRIVATE);
    }


}