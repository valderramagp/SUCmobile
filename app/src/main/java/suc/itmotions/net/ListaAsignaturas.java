package suc.itmotions.net;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import layout.FragmentoDetalleAsignatura;
import layout.FragmentoListaAsignaturas;
import suc.itmotions.net.entities.DetalleAsignatura;

public class ListaAsignaturas extends AppCompatActivity{

    private boolean dosPaneles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_asignaturas);
        Toolbar myToolbar = ((Toolbar) findViewById(R.id.my_toolbar));
        setSupportActionBar(myToolbar);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsingToolbarLayout.setTitle("¡Bienvenido!");

        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, FragmentoListaAsignaturas.crear()).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MisDatos:

                break;

            case R.id.CerrarSesion:
                SharedPreferences prefs = getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("login", false);
                editor.apply();
                Intent intent = new Intent();
                intent.setClass(this, LoginActivity.class);
                finish();
                startActivity(intent);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
