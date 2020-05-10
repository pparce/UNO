package cu.uno.utiles;

import android.app.Application;
import android.database.Cursor;
import android.graphics.BitmapFactory;


import cu.uno.datos.Basedatos;
import cu.uno.datos.DB;
import cu.uno.modelos.ModeloCategoria;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App extends Application {
    public static List<ModeloCategoria> LISTA_CATEGORIAS;

    Basedatos basedatos;


    @Override
    public void onCreate() {
        super.onCreate();
        crearBaseDatos();
        cargarCategorias();

    }

    private void crearBaseDatos() {
        basedatos = new Basedatos(this, "datos.db");
        try {
            basedatos.createDataBase("/data/user/0/cu.uno/databases/datos.db");
        } catch (IOException e) {
        }
    }

    private void cargarCategorias() {
        Cursor cursor = DB.getInstance(this).getTabla("CATEGORIAS");
        List<ModeloCategoria> listaCategorias = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            /**set value to list one by one**/
            while (cursor.moveToNext()) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                ModeloCategoria modelo = new ModeloCategoria();
                modelo.setId(cursor.getInt(0));
                modelo.setNombre(cursor.getString(1));
                listaCategorias.add(modelo);
            }
        }
        App.LISTA_CATEGORIAS = listaCategorias;
    }
}
