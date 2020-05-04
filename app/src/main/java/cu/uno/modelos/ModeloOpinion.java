package cu.uno.modelos;

import android.graphics.Bitmap;

public class ModeloOpinion {

    int id = 0;
    String usuario = "";
    Bitmap avatar = null;
    float evaluacion = 0;
    String mensaje = "";

    public ModeloOpinion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario){
        this.usuario = usuario;
    }

    public Bitmap getAvatar() {
        return avatar;
    }

    public void setAvatar(Bitmap avatar){
        this.avatar = avatar;
    }

    public float getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(float evaluacion){
        this.evaluacion = evaluacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje){
        this.mensaje = mensaje;
    }

}

