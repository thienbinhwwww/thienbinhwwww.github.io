package com.example.learnforeignlanguage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.learnforeignlanguage.DAO.CustomDetailDao;
import com.example.learnforeignlanguage.mode.CustomDetail;

import java.util.List;

public class MenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        AnhXa();
    }

    //Ánh xạ các biến
    private void AnhXa() {

    }

//    Ân vào phần biểu tượng người dùng
    public void clickUser(View view){
        Intent intent = new Intent(MenuActivity.this,UsesActivity.class);
        startActivity(intent);
    }

//    Chức năng bài học chủ đề
    public void baiHocChuDe(View view){
        List<CustomDetail> list;
        CustomDetailDao customDetailDao = new CustomDetailDao(this);
        list = customDetailDao.getAllCustomDetail();
        for (int i=0;i<list.size();i++){
            Toast.makeText(this,list.get(i).getIdCustomDetail()+"-"+list.get(i).getIdCustom()+"-"+list.get(i).getIdVocabulary(),Toast.LENGTH_LONG).show();
        }
    }

//    Chức năng bài học tự chọn
    public void  baiHocTuChon(View view){
        MediaPlayer mediaPlayer = MediaPlayer.create(this, Uri.parse("https://soundoftext.nyc3.digitaloceanspaces.com/24dd8160-c7d4-11e7-9df0-2f554923557b.mp3"));
        mediaPlayer.start();
    }

//    Chức năng lịch sử học
    public void lichSuHoc(View view){
        try {
            MediaPlayer mediaPlayer = new MediaPlayer();
            Uri uri = Uri.parse("https://soundoftext.nyc3.digitaloceanspaces.com/24dd8160-c7d4-11e7-9df0-2f554923557b.mp3");
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(MenuActivity.this,uri);
            mediaPlayer.prepare();
            mediaPlayer.start();
        }catch (Exception ex){
            Log.d("s",ex.toString());
        }
    }
}