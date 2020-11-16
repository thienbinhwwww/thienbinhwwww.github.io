package com.example.learnforeignlanguage.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.app.Activity;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.learnforeignlanguage.Database.Database;
import com.example.learnforeignlanguage.Database.Sqlite;
import com.example.learnforeignlanguage.mode.Custom;

import java.util.ArrayList;
import java.util.List;

public class CustomDao {
    final String DATABASE_NAME = "dataLearnForeignLanguage.sqlite";
    SQLiteDatabase sqLiteDatabase;
    Activity context;

    public CustomDao(Activity context) {
        this.context = context;
    }

    public boolean addCustom(Custom custom){
        sqLiteDatabase = Database.initDatabase(context,DATABASE_NAME);
        // ghép cặp giá trị vào tên cột 2
        ContentValues contentValues = new ContentValues();
        contentValues.put("idCustom",custom.getIdCustom());

        long kq = sqLiteDatabase.insert("Custom", null, contentValues);

        if (kq > 0){
            return true;
        } else{
            return false;
        }
    }

    public boolean updateCustom(Custom custom){
        sqLiteDatabase = Database.initDatabase(context,DATABASE_NAME);
        ContentValues contentValues = new ContentValues();
        contentValues.put("idCustom",custom.getIdCustom());
        // truy vấn 3
        long kq = sqLiteDatabase.update("Custom", contentValues, "idCustom ="+new int[]{custom.getIdCustom()},null);

        if (kq > 0){
            return true;
        } else{
            return false;
        }
    }

    public boolean deleteCustom(int id) {
        sqLiteDatabase = Database.initDatabase(context,DATABASE_NAME);
        // truy vấn 3
        long kq = sqLiteDatabase.delete("Custom", "idCustom =" + new int[]{id}, null);

        if (kq > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Custom> getAllCustom(){
        sqLiteDatabase = Database.initDatabase(context,DATABASE_NAME);
        List<Custom> list = new ArrayList<>();
        String sql = "SELECT * FROM Custom";
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        list.clear();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int ID = cursor.getInt(0);

                Custom custom = new Custom();
                custom.setIdCustom(ID);

                list.add(custom);
                cursor.moveToNext();
            }
        }
        return list;
    }

    public List<Custom> timKiem(String datetk) {
        List<Custom> list = new ArrayList<>();
        String sql = "SELECT * FROM Custom WHERE idCustom LIKE '%" + datetk + "%'";

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        list.clear();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int ID = cursor.getInt(0);

                Custom custom = new Custom();
                custom.setIdCustom(ID);

                list.add(custom);
                cursor.moveToNext();
            }
        }
        return list;
    }
}
