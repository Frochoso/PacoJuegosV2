package com.mygdx.pacojuegos;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mygdx.pacojuegos.repository.Conexiones;
import com.mygdx.pacojuegos.repository.DataUploader;
import com.mygdx.pacojuegos.repository.Player;

public class AndroidInterface implements Conexiones {

    private FirebaseDatabase database;
    private DatabaseReference player;

    public AndroidInterface() {
        database = FirebaseDatabase.getInstance("https://pacogames-2f23f-default-rtdb.europe-west1.firebasedatabase.app/");
        player = database.getReference("");
    }

    @Override
    public void insertarDatos() {
        player = database.getReference("player/" + recortaNombre(Player.nombre));
        if (DataUploader.devuelvePuntuacion() > 0) {
            player.setValue(DataUploader.devuelvePuntuacion());
        }

    }

    @Override
    public void muestraNombreJugador() {
        player.child("player/").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Player player = Player.getInstance();
                player.getJugadores().clear();

                for (DataSnapshot campos : snapshot.getChildren()) {
                    String clave = snapshot.getKey();
                    String nombre = campos.getKey();
                    Integer puntuacionInteger = campos.getValue(Integer.class);
                    if (puntuacionInteger != null && !player.equals(null)) {
                        player.getJugadores().add(new Player(clave, nombre, puntuacionInteger));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public String recortaNombre(String nombre) {
        return nombre.substring(0, 3).toUpperCase();
    }

}
