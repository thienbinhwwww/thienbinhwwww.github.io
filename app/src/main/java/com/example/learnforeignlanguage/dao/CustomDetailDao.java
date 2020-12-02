package com.example.learnforeignlanguage.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.learnforeignlanguage.database.Database;

import com.example.learnforeignlanguage.mode.CustomDetail;

import java.util.ArrayList;
import java.util.List;

public class CustomDetailDao {
    final String DATABASE_NAME = "dataLearnForeignLanguage.sqlite";
    SQLiteDatabase sqLiteDatabase;
    Activity context;

    public CustomDetailDao(Activity context) {
        this.context = context;
    }

    public boolean addCustomDetail(CustomDetail customDetail){
        sqLiteDatabase = Database.initDatabase(context,DATABASE_NAME);
        // ghép cặp giá trị vào tên cột 2
        ContentValues contentValues = new ContentValues();
        contentValues.put("idCustom",customDetail.getIdCustom());
        contentValues.put("idVocabulary",customDetail.getIdVocabulary());

        long kq = sqLiteDatabase.insert("CustomDetail", null, contentValues);

        if (kq > 0){
            return true;
        } else{
            return false;
        }
    }

    public boolean updateCustomDetail(CustomDetail customDetail){
        sqLiteDatabase = Database.initDatabase(context,DATABASE_NAME);
        // ghép cặp giá trị vào tên cột 2
        ContentValues contentValues = new ContentValues();
        contentValues.put("idCustomDetail",customDetail.getIdCustomDetail());
        contentValues.put("idCustom",customDetail.getIdCustom());
        contentValues.put("idVocabulary",customDetail.getIdVocabulary());
        // truy vấn 3
        long kq = sqLiteDatabase.update("CustomDetail", contentValues, "idCustomDetail ="+new int[]{customDetail.getIdCustomDetail()},null);

        if (kq > 0){
            return true;
        } else{
            return false;
        }
    }

    public boolean deleteCustomDetail(int id) {
        sqLiteDatabase = Database.initDatabase(context,DATABASE_NAME);
        // truy vấn 3
        long kq = sqLiteDatabase.delete("CustomDetail", "idCustomDetail =" + new int[]{id}, null);

        if (kq > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<CustomDetail> getAllCustomDetail(){
        sqLiteDatabase = Database.initDatabase(context,DATABASE_NAME);
        List<CustomDetail> list = new ArrayList<>();
        String sql = "SELECT * FROM CustomDetail";
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        list.clear();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int ID = cursor.getInt(0);
                int IDCustom = cursor.getInt(1);
                int IDVocabulary = cursor.getInt(2);

                CustomDetail customDetail = new CustomDetail();
                customDetail.setIdCustomDetail(ID);
                customDetail.setIdCustom(IDCustom);
                customDetail.setIdVocabulary(IDVocabulary);

                list.add(customDetail);
                cursor.moveToNext();
            }
        }
        return list;
    }

    public List<CustomDetail> timKiem(int datetk) {
        sqLiteDatabase = Database.initDatabase(context,DATABASE_NAME);
        List<CustomDetail> list = new ArrayList<>();
        String sql = "SELECT * FROM CustomDetail WHERE idCustom LIKE '" + datetk + "'";

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        list.clear();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int ID = cursor.getInt(0);
                int IDCustomDetail = cursor.getInt(1);
                int IDVocabulary = cursor.getInt(2);

                CustomDetail customDetail = new CustomDetail();
                customDetail.setIdCustomDetail(ID);
                customDetail.setIdCustom(IDCustomDetail);
                customDetail.setIdVocabulary(IDVocabulary);

                list.add(customDetail);
                cursor.moveToNext();
            }
        }
        return list;
    }
}
