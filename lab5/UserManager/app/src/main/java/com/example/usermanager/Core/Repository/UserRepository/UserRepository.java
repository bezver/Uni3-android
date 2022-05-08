package com.example.usermanager.Core.Repository.UserRepository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.usermanager.Core.DbHelper;
import com.example.usermanager.Core.Models.User;
import com.example.usermanager.R;


import java.util.ArrayList;

public class UserRepository implements IUserRepository {
    private DbHelper dbHelper;
    private SQLiteDatabase database;

    public UserRepository(Context context) {
        dbHelper = new DbHelper(context.getApplicationContext());
    }

    @Override
    public ArrayList<User> GetAll() {
        open();
        ArrayList<User> users = new ArrayList<>();
        Cursor cursor = getAllEntries();
        while (cursor.moveToNext()) {
            users.add(readUser(cursor));
        }
        cursor.close();
        close();
        return users;
    }

    @Override
    public User GetById(Integer id) {
        open();
        User user = null;
        String query = String.format("SELECT * FROM %s WHERE %s=?",DbHelper.TABLE, DbHelper.COLUMN_ID);
        Cursor cursor = database.rawQuery(query, new String[]{ String.valueOf(id)});
        if(cursor.moveToFirst()){
            user = readUser(cursor);
        }
        cursor.close();
        close();
        return user;
    }

    @Override
    public User Add(User user) {
        user.setIconKey(R.drawable.face);
        open();
        ContentValues cv = new ContentValues();
        writeUser(cv, user);
        Long id = database.insert(DbHelper.TABLE, null, cv);
        close();
        if (id == -1) {
            return null;
        }
        return GetById(id.intValue());
    }



    @Override
    public User Update(User user) {
        user.setIconKey(R.drawable.face);
        open();
        String whereClause = DbHelper.COLUMN_ID + "=" + user.getId();
        ContentValues cv = new ContentValues();
        writeUser(cv, user);
        database.update(DbHelper.TABLE, cv, whereClause, null);
        close();
        return GetById(user.getId());
    }

    @Override
    public void Delete(Integer id) {
        open();
        String whereClause = "_id = ?";
        String[] whereArgs = new String[] {String.valueOf(id)};
        database.delete(DbHelper.TABLE, whereClause, whereArgs);
        close();
    }

    private Cursor getAllEntries(){
        String[] columns = new String[] {
                DbHelper.COLUMN_ID, DbHelper.COLUMN_FIRSTNAME,
                DbHelper.COLUMN_LASTNAME, DbHelper.COLUMN_EMAIL,
                DbHelper.COLUMN_ADDRESS, DbHelper.COLUMN_ICONKEY };
        return  database.query(DbHelper.TABLE, columns, null, null, null, null, null);
    }

    private void writeUser(ContentValues cv, User user) {
//        cv.put(DbHelper.COLUMN_ID, user.getId());
        cv.put(DbHelper.COLUMN_FIRSTNAME, user.getFirstname());
        cv.put(DbHelper.COLUMN_LASTNAME, user.getLastname());
        cv.put(DbHelper.COLUMN_EMAIL, user.getEmail());
        cv.put(DbHelper.COLUMN_ADDRESS, user.getAddress());
        cv.put(DbHelper.COLUMN_ICONKEY, user.getIconKey());
    }

    private User readUser(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(DbHelper.COLUMN_ID));
        String firstname = cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_FIRSTNAME));
        String lastname = cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_LASTNAME));
        String email = cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_EMAIL));
        String address = cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_ADDRESS));
        int iconKey = cursor.getInt(cursor.getColumnIndex(DbHelper.COLUMN_ICONKEY));
        return new User(id, firstname, lastname, email, address, iconKey);
    }

    private void open() {
        database = dbHelper.getWritableDatabase();
    }

    private void close(){
        dbHelper.close();
    }
}
