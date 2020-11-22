package com.example.learnforeignlanguage.DAO;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.learnforeignlanguage.Database.Database;
import com.example.learnforeignlanguage.mode.User;


import java.util.ArrayList;
import java.util.List;

public class UserDao {
    final String DATABASE_NAME = "dataLearnForeignLanguage.sqlite";
    SQLiteDatabase sqLiteDatabase;
    Activity context;

    public UserDao(Activity context) {
        this.context = context;
    }

    public boolean addUser(User user){
        sqLiteDatabase = Database.initDatabase(context,DATABASE_NAME);
        // ghép cặp giá trị vào tên cột 2
        ContentValues contentValues = new ContentValues();
        contentValues.put("Email",user.getEmail());
        contentValues.put("PhoneNumber",user.getPhoneNumber());
        contentValues.put("UserName",user.getUserName());
        contentValues.put("Password",user.getPassword());

        long kq = sqLiteDatabase.insert("User", null, contentValues);

        if (kq > 0){
            return true;
        } else{
            return false;
        }
    }

    public boolean updateUser(User user){
        sqLiteDatabase = Database.initDatabase(context,DATABASE_NAME);
        // ghép cặp giá trị vào tên cột 2
        ContentValues contentValues = new ContentValues();
        contentValues.put("idUser",user.getIdUser());
        contentValues.put("Email",user.getEmail());
        contentValues.put("PhoneNumber",user.getPhoneNumber());
        contentValues.put("UserName",user.getUserName());
        contentValues.put("Password",user.getPassword());
        // truy vấn 3
        long kq = sqLiteDatabase.update("User", contentValues, "idUser ="+new int[]{user.getIdUser()},null);

        if (kq > 0){
            return true;
        } else{
            return false;
        }
    }

    public boolean deleteUser(int id) {
        sqLiteDatabase = Database.initDatabase(context,DATABASE_NAME);
        // truy vấn 3
        long kq = sqLiteDatabase.delete("User", "idUser =" + new int[]{id}, null);

        if (kq > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<User> getAllUser(){
        sqLiteDatabase = Database.initDatabase(context,DATABASE_NAME);
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM User";
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        list.clear();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int IDUser = cursor.getInt(0);
                String Email = cursor.getString(1);
                String PhoneNumber = cursor.getString(2);
                String UserName = cursor.getString(3);
                String Password = cursor.getString(4);

                User user = new User();
                user.setIdUser(IDUser);
                user.setEmail(Email);
                user.setPhoneNumber(PhoneNumber);
                user.setUserName(UserName);
                user.setPassword(Password);

                list.add(user);
                cursor.moveToNext();
            }
        }
        return list;
    }

    public List<User> timKiem(int datetk) {
        sqLiteDatabase = Database.initDatabase(context,DATABASE_NAME);
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM User WHERE idUser LIKE '%" + datetk + "%'";

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        list.clear();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int IDUser = cursor.getInt(0);
                String Email = cursor.getString(1);
                String PhoneNumber = cursor.getString(2);
                String UserName = cursor.getString(3);
                String Password = cursor.getString(4);

                User user = new User();
                user.setIdUser(IDUser);
                user.setEmail(Email);
                user.setPhoneNumber(PhoneNumber);
                user.setUserName(UserName);
                user.setPassword(Password);

                list.add(user);
                cursor.moveToNext();
            }
        }
        return list;
    }

    public List<User> timUserName(String userName){
        sqLiteDatabase = Database.initDatabase(context,DATABASE_NAME);
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM User WHERE UserName LIKE '%" + userName + "%'";
            Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
            list.clear();
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    int IDUser = cursor.getInt(0);
                    String Email = cursor.getString(1);
                    String PhoneNumber = cursor.getString(2);
                    String UserName = cursor.getString(3);
                    String Password = cursor.getString(4);

                    User user = new User();
                    user.setIdUser(IDUser);
                    user.setEmail(Email);
                    user.setPhoneNumber(PhoneNumber);
                    user.setUserName(UserName);
                    user.setPassword(Password);

                    list.add(user);
                    cursor.moveToNext();
                }
            }

        return list;
    }
}
