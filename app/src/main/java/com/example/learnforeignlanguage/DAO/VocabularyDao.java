package com.example.learnforeignlanguage.DAO;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.learnforeignlanguage.Database.Database;
import com.example.learnforeignlanguage.mode.Vocabulary;


import java.util.ArrayList;
import java.util.List;

public class VocabularyDao {
    final String DATABASE_NAME = "dataLearnForeignLanguage.sqlite";
    SQLiteDatabase sqLiteDatabase;
    Activity context;

    public VocabularyDao(Activity context) {
        this.context = context;
    }

    public boolean addVocabulary(Vocabulary vocabulary){
        sqLiteDatabase = Database.initDatabase(context,DATABASE_NAME);
        // ghép cặp giá trị vào tên cột 2
        ContentValues contentValues = new ContentValues();
        contentValues.put("LdCustomDetail",vocabulary.getIdVocabulary());
        contentValues.put("Vocabulary",vocabulary.getVocabulary());
        contentValues.put("Means",vocabulary.getMeans());
        contentValues.put("Pronoune",vocabulary.getPronounce());

        long kq = sqLiteDatabase.insert("Vocabulary", null, contentValues);

        if (kq > 0){
            return true;
        } else{
            return false;
        }
    }

    public boolean updateVocabulary(Vocabulary vocabulary){
        sqLiteDatabase = Database.initDatabase(context,DATABASE_NAME);
        // ghép cặp giá trị vào tên cột 2
        ContentValues contentValues = new ContentValues();
        contentValues.put("LdCustomDetail",vocabulary.getIdVocabulary());
        contentValues.put("Vocabulary",vocabulary.getVocabulary());
        contentValues.put("Means",vocabulary.getMeans());
        contentValues.put("Pronoune",vocabulary.getPronounce());
        // truy vấn 3
        long kq = sqLiteDatabase.update("Vocabulary", contentValues, "idVocabulary ="+new int[]{vocabulary.getIdVocabulary()},null);

        if (kq > 0){
            return true;
        } else{
            return false;
        }
    }

    public boolean deleteVocabulary(int id) {
        sqLiteDatabase = Database.initDatabase(context,DATABASE_NAME);
        // truy vấn 3
        long kq = sqLiteDatabase.delete("Vocabulary", "idVocabulary =" + new int[]{id}, null);

        if (kq > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Vocabulary> getAllVocabulary(){
        sqLiteDatabase = Database.initDatabase(context,DATABASE_NAME);
        List<Vocabulary> list = new ArrayList<>();
        String sql = "SELECT * FROM Vocabulary";
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        list.clear();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int IDVocabulary = cursor.getInt(0);
                String Vocabulary= cursor.getString(1);
                String Means = cursor.getString(2);
                String Pronoune = cursor.getString(3);

                Vocabulary vocabulary = new Vocabulary();
                vocabulary.setIdVocabulary(IDVocabulary);
                vocabulary.setVocabulary(Vocabulary);
                vocabulary.setMeans(Means);
                vocabulary.setPronounce(Pronoune);

                list.add(vocabulary);
                cursor.moveToNext();
            }
        }
        return list;
    }

    public List<Vocabulary> timKiem(int datetk) {
        sqLiteDatabase = Database.initDatabase(context,DATABASE_NAME);
        List<Vocabulary> list = new ArrayList<>();
        String sql = "SELECT * FROM Vocabulary WHERE idVocabulary LIKE '%" + datetk + "%'";

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        list.clear();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int IDVocabulary = cursor.getInt(0);
                String Vocabulary= cursor.getString(1);
                String Means = cursor.getString(2);
                String Pronoune = cursor.getString(3);

                Vocabulary vocabulary = new Vocabulary();
                vocabulary.setIdVocabulary(IDVocabulary);
                vocabulary.setVocabulary(Vocabulary);
                vocabulary.setMeans(Means);
                vocabulary.setPronounce(Pronoune);

                list.add(vocabulary);
                cursor.moveToNext();
            }
        }
        return list;
    }
}
