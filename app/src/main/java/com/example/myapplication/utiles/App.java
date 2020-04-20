package com.example.myapplication.utiles;

import android.app.Application;


import com.example.myapplication.modelos.ModeloCategoria;

import java.util.List;

public class App extends Application {
    public static List<ModeloCategoria> listaCategorias;
    @Override
    public void onCreate() {
        super.onCreate();


    }
}
