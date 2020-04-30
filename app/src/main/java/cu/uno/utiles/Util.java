package cu.uno.utiles;

import android.app.AlertDialog;
import android.content.Context;

public class Util {

    public static void showDialogSimple(Context context, String title, String mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                builder.setTitle(title);
                builder.setMessage(mensaje);
                builder.setPositiveButton("Aceptar", null);
                builder.create().show();

    }
}
