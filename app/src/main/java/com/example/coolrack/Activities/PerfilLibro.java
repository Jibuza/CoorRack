package com.example.coolrack.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coolrack.R;
import com.example.coolrack.generalClass.ImagesManagers.BitmapManager;
import com.example.coolrack.generalClass.Libro;

public class PerfilLibro extends AppCompatActivity {
    private Libro libro = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_libro);

        // Crea un boton con forma de flecha el la toolbar
        // esta se usara para retroceder
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // carga el pojo enviado por  la Actividad/fragmento que cargo esta Activity anteriormente
        Bundle bundle = getIntent().getExtras();
        this.libro = (Libro) bundle.getSerializable("objetoLibro");
        this.setTitle(libro.getTitle());

        // Carga los datos del pojo para para poder verse
        cargarDatos(libro);

    }

    // Carga los datos del POJO libro al XML
    public void cargarDatos(Libro l){
        // Inicializa los TextViews y el imageView
        TextView titulo = (TextView) findViewById( R.id.titulo_perfil);
        TextView autor = (TextView) findViewById( R.id.autor_perfil);
        TextView serie = (TextView) findViewById( R.id.serie_perfil);
        TextView lenguaje = (TextView) findViewById( R.id.lenguaje_perfil);
        TextView identificador = (TextView) findViewById( R.id.identificador_perfil);
        TextView url = (TextView) findViewById( R.id.url_perfil);
        ImageView imagen = (ImageView) findViewById( R.id.imagen_Perfil);

        // setea los datos
        titulo.setText(l.getTitle());
        autor.setText(l.getAuthor());
        serie.setText(l.getSerie());
        lenguaje.setText(l.getLanguage());
        identificador.setText(l.getIdentifier());
        url.setText(l.getUrl());
        imagen.setImageBitmap(new BitmapManager().bitmapUncompress(l.getImg()));

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), com.example.coolrack.Activities.LecturaActivity.class)
                        .putExtra("epub_location", l.getUrl())
                );
            }
        });

    }

    // Redirecciona al usuario dependiendo de la opcion selecionada en la toolbar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return true;
    }
}