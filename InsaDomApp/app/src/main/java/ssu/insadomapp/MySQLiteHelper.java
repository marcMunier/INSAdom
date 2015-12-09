package ssu.insadomapp;

/**
 * Created by Marc on 02/12/2015.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper{


    public static final String TABLE_NAME = "InsaDom_table_ssu_insadom";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_URL = "URL";

    private static final String DATABASE_NAME = "InsaDom.db";
    private static final int DATABASE_VERSION = 3;

    // Commande sql pour la création de la base de données
    private static final String DATABASE_CREATE = "create table " + TABLE_NAME
            + "("   + COLUMN_ID + " integer primary key autoincrement, "
                    + COLUMN_NAME   + " text not null,"
                    + COLUMN_URL    + " text not null"
 //                   + COLUMN_CODE   + "INTERGER,"
            +");";

//    private static final String DATABASE_CREATE = "create table "
//            + TABLE_NAME + "(" + COLUMN_ID
//            + " integer primary key autoincrement, " + "comment"
//            + " text not null);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        Log.i("DEBUG_MUNIER", "onCreate de database" );
         database.execSQL(DATABASE_CREATE);
        Log.i("DEBUG_MUNIER", "onCreate de database" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}
