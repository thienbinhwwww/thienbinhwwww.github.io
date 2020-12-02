package com.example.learnforeignlanguage.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.learnforeignlanguage.database.Database;
import com.example.learnforeignlanguage.mode.Theme;


import java.util.ArrayList;
import java.util.List;

public class ThemeDao {
    final String DATABASE_NAME = "dataLearnForeignLanguage.sqlite";
    SQLiteDatabase sqLiteDatabase;
    Activity context;

    public ThemeDao(Activity context) {
        this.context = context;
    }

    public boolean addTheme(Theme theme){
        sqLiteDatabase = Database.initDatabase(context,DATABASE_NAME);
        // ghép cặp giá trị vào tên cột 2
        ContentValues contentValues = new ContentValues();
        contentValues.put("idCustom",theme.getIdCustom());
        contentValues.put("theme",theme.getTheme());

        long kq = sqLiteDatabase.insert("Theme", null, contentValues);

        if (kq > 0){
            return true;
        } else{
            return false;
        }
    }


    public boolean updateTheme(Theme theme){
        sqLiteDatabase = Database.initDatabase(context,DATABASE_NAME);
        // ghép cặp giá trị vào tên cột 2
        ContentValues contentValues = new ContentValues();
        contentValues.put("idTheme",theme.getTheme());
        contentValues.put("idCustom",theme.getIdCustom());
        contentValues.put("theme",theme.getTheme());
        // truy vấn 3
        long kq = sqLiteDatabase.update("Theme", contentValues, "idTheme ="+new int[]{theme.getIdTheme()},null);

        if (kq > 0){
            return true;
        } else{
            return false;
        }
    }

    public boolean deleteTheme(int id) {
        sqLiteDatabase = Database.initDatabase(context,DATABASE_NAME);
        // truy vấn 3
        long kq = sqLiteDatabase.delete("Theme", "idTheme =" + new int[]{id}, null);

        if (kq > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Theme> getAllTheme(){
        sqLiteDatabase = Database.initDatabase(context,DATABASE_NAME);
        List<Theme> list = new ArrayList<>();
        String sql = "SELECT * FROM Theme";
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        list.clear();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int IDTheme = cursor.getInt(0);
                int IDCustom = cursor.getInt(1);
                String themes = cursor.getString(2);

                Theme theme = new Theme();
                theme.setIdTheme(IDTheme);
                theme.setIdCustom(IDCustom);
                theme.setTheme(themes);

                list.add(theme);
                cursor.moveToNext();
            }
        }
        return list;
    }

    public List<Theme> timKiem(int datetk) {
        sqLiteDatabase = Database.initDatabase(context,DATABASE_NAME);
        List<Theme> list = new ArrayList<>();
        String sql = "SELECT * FROM Theme WHERE idTheme LIKE '%" + datetk + "%'";

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        list.clear();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int IDTheme = cursor.getInt(0);
                int IDCustom = cursor.getInt(1);
                String themes = cursor.getString(2);

                Theme theme = new Theme();
                theme.setIdTheme(IDTheme);
                theme.setIdCustom(IDCustom);
                theme.setTheme(themes);

                list.add(theme);
                cursor.moveToNext();
            }
        }
        return list;
    }
}
