package com.example.learnforeignlanguage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.learnforeignlanguage.dao.CustomDetailDao;
import com.example.learnforeignlanguage.dao.VocabularyDao;
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
    Dialog dialogD;
    TextView tv_d;

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
        createGame();
    }

    private void createGame() {
        listMeans.clear();
        for (int i=0;i<listVocabulary.size();i++){
            listMeans.add(listVocabulary.get(i).getMeans());
        }
        if(temp>=10){

            Dialog dialog = new Dialog(GameIActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.layout_dialog_kq);
            TextView tv_poit = dialog.findViewById(R.id.tv_dialog_kq);
            tv_poit.setText(String.valueOf(poit));
            Button button = dialog.findViewById(R.id.btn_dialg_ok);
            dialog.show();

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    finish();
                }
            });

        }else {
            if (ramdom(temp)) {
                layout_tich.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        poit++;
                        temp++;
                        tv_d.setText("Bạn trả lời đúng");
                        dialogD.show();
                        createGame();
                    }
                });
                layout_x.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        temp++;
                        tv_d.setText("Bạn trả sai rồi");
                        dialogD.show();
                        createGame();
                    }
                });
            } else {

                layout_tich.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        temp++;
                        tv_d.setText("Bạn trả sai rồi");
                        dialogD.show();
                        createGame();
                    }
                });
                layout_x.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        poit++;
                        temp++;
                        tv_d.setText("Bạn trả lời đúng");
                        dialogD.show();
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
            for (int j=0;j<listMeans.size();j++){
                if(listMeans.get(j).equals(listVocabulary.get(id).getMeans())){
                    listMeans.remove(j);
                }
            }
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
        dialogD = new Dialog(this);
        dialogD.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogD.setContentView(R.layout.layout_dialog_d);
        tv_d=dialogD.findViewById(R.id.tv_dialog_d);
    }


}