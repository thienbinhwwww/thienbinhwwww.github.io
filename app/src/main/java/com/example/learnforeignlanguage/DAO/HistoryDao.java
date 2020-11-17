package com.example.learnforeignlanguage.DAO;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.learnforeignlanguage.Database.Database;
import com.example.learnforeignlanguage.mode.History;


import java.util.ArrayList;
import java.util.List;

public class HistoryDao {
    final String DATABASE_NAME = "dataLearnForeignLanguage.sqlite";
    SQLiteDatabase sqLiteDatabase;
    Activity context;

    public HistoryDao(Activity context) {
        this.context = context;
    }

    public boolean addHistory(History history){
        sqLiteDatabase = Database.initDatabase(context,DATABASE_NAME);
        // ghép cặp giá trị vào tên cột 2
        ContentValues contentValues = new ContentValues();
        contentValues.put("idHistory",history.getIdHistory());
        contentValues.put("idCustom",history.getIdCustom());
        contentValues.put("idUser",history.getIdUser());

        long kq = sqLiteDatabase.insert("History", null, contentValues);

        if (kq > 0){
            return true;
        } else{
            return false;
        }
    }

    public boolean updateHistory(History history){
        sqLiteDatabase = Database.initDatabase(context,DATABASE_NAME);
        // ghép cặp giá trị vào tên cột 2
        ContentValues contentValues = new ContentValues();
        contentValues.put("idHistory",history.getIdHistory());
        contentValues.put("idCustom",history.getIdCustom());
        contentValues.put("idUser",history.getIdUser());
        // truy vấn 3
        long kq = sqLiteDatabase.update("History", contentValues, "idHistory ="+new int[]{history.getIdHistory()},null);

        if (kq > 0){
            return true;
        } else{
            return false;
        }
    }

    public boolean deleteHistory(int id) {
        sqLiteDatabase = Database.initDatabase(context,DATABASE_NAME);
        // truy vấn 3
        long kq = sqLiteDatabase.delete("History", "idHistory =" + new int[]{id}, null);

        if (kq > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<History> getAllHistory(){
        sqLiteDatabase = Database.initDatabase(context,DATABASE_NAME);
        List<History> list = new ArrayList<>();
        String sql = "SELECT * FROM History";
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        list.clear();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int IDHistory = cursor.getInt(0);
                int IDCustom = cursor.getInt(1);
                int IDUser = cursor.getInt(2);

                History history = new History();
                history.setIdHistory(IDHistory);
                history.setIdCustom(IDCustom);
                history.setIdUser(IDUser);

                list.add(history);
                cursor.moveToNext();
            }
        }
        return list;
    }

    public List<History> timKiem(int datetk) {
        sqLiteDatabase = Database.initDatabase(context,DATABASE_NAME);
        List<History> list = new ArrayList<>();
        String sql = "SELECT * FROM History WHERE idHistory LIKE '%" + datetk + "%'";

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        list.clear();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int IDHistory = cursor.getInt(0);
                int IDCustom = cursor.getInt(1);
                int IDUser = cursor.getInt(2);

                History history = new History();
                history.setIdHistory(IDHistory);
                history.setIdCustom(IDCustom);
                history.setIdUser(IDUser);

                list.add(history);
                cursor.moveToNext();
            }
        }
        return list;
    }
}
