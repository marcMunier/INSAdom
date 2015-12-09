package ssu.insadomapp;


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Marc on 02/12/2015.
 */
public class HomeDataSource {

    // Champs de la base de données
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
                                    MySQLiteHelper.COLUMN_NAME,
                                    MySQLiteHelper.COLUMN_URL };

    public HomeDataSource(Context context) {
//        Log.i("DEBUG_MUNIER", "HomeDataSource: start of constructor" );
        dbHelper = new MySQLiteHelper(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
 //       Log.i("DEBUG_MUNIER", "HomeDataSource: end of constructor" );

    }

    public void close() {
        dbHelper.close();
    }

    public void createHome(Home home) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_NAME, home.getName());
        values.put(MySQLiteHelper.COLUMN_URL, home.getURL());
        long insertId = database.insert(MySQLiteHelper.TABLE_NAME, null,
                values);
        //Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME,
        //        allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
        //        null, null, null);
        //cursor.moveToFirst();
       // Home newHome = cursorToHome(cursor);
        //cursor.close();
        Log.i("DEBUG_MUNIER", "HomeDataSource : creHome " + home.getName());

       // return newHome;
    }

    public void deleteHome( long id) {
        System.out.println("Home deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_NAME, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public void deleteAllHome( ) {
        System.out.println("All Home will be deleted ");
        database.delete(MySQLiteHelper.TABLE_NAME,
                null,
                //MySQLiteHelper.COLUMN_ID + " = " + "*",
                null);
    }


    public List<Home> getAllHome() {
        List<Home> homes = new ArrayList<Home>();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Home home = cursorToHome(cursor);
            homes.add(home);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return homes;
    }

    private Home cursorToHome(Cursor cursor) {
        Home home = new Home();
        home.setId(cursor.getLong(0));
        home.setName(cursor.getString(1));
        home.setURL(cursor.getString(2));
        return home;
    }

}
