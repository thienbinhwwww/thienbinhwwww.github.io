package com.example.learnforeignlanguage.adapter;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learnforeignlanguage.R;
import com.example.learnforeignlanguage.mode.Vocabulary;

import java.util.List;

public class AdapterVocabulary extends BaseAdapter {
    List<Vocabulary> listVocabulary;
    Context context;

    public AdapterVocabulary(List<Vocabulary> listVocabulary, Context context) {
        this.listVocabulary = listVocabulary;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listVocabulary.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_gridview_list_vocabulary,viewGroup,false);
        TextView tv_vocabulary = view.findViewById(R.id.tv_gridView_listVocabulary_v);
        TextView tv_m = view.findViewById(R.id.tv_gridView_listVocabulary_m);
        ImageView btn_at = view.findViewById(R.id.btn_at);

        tv_vocabulary.setText(listVocabulary.get(i).getVocabulary());
        tv_m.setText(listVocabulary.get(i).getMeans());
        btn_at.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    Uri uri = Uri.parse(listVocabulary.get(i).getPronounce());
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setDataSource(context,uri);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                }catch (Exception ex){
                    Log.d("s",ex.toString());
                }
            }
        });

        return view;
    }
}
