package cu.uno.datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Basedatos extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    private final Context context;


    public Basedatos(Context context, String name) {
        super(context, name, null, 2);
        this.context = context;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.disableWriteAheadLogging();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void createDataBase(String finalPath) throws IOException {
        boolean dbExist = checkDataBase(finalPath);
        if (dbExist) {

        } else {
            this.getReadableDatabase();
            this.close();

            try {
                copyDataBase(finalPath);
            } catch (IOException e) {
                throw new Error("Error copiando la base de datos");
            }

        }
    }

    public boolean checkDataBase(String path) {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = path;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    private void copyDataBase(String finalPath) throws IOException {
        InputStream myInput = context.getAssets().open("datos.db");
        String outFileName = finalPath;

        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int lenght;
        while ((lenght = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, lenght);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }


}

