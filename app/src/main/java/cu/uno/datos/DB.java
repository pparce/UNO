package cu.uno.datos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DB {

    private SQLiteDatabase dataBase;

    @SuppressLint("StaticFieldLeak")
    private static DB instance;

    private DB(Context context) {
        dataBase = SQLiteDatabase.openDatabase("/data/user/0/cu.uno/databases/datos.db", null, SQLiteDatabase.OPEN_READONLY);
    }

    public static DB getInstance(Context context) {
        if (instance == null) {
            instance = new DB(context);
        }
        return instance;
    }

    public Cursor getTabla(String tabla) {
        return dataBase.rawQuery("SELECT * FROM " + tabla, null);
    }
}