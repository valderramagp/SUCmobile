package suc.itmotions.net;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import layout.FragmentoDetalleAsignatura;
import layout.FragmentoListaAsignaturas;
import suc.itmotions.net.entities.DetalleAsignatura;

public class ListaAsignaturas extends AppCompatActivity implements FragmentoListaAsignaturas.EscuchaFragmento {

    private boolean dosPaneles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_asignaturas);

        ((Toolbar) findViewById(R.id.my_toolbar)).setTitle("¡Bienvenido!");

        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, FragmentoListaAsignaturas.crear()).commit();

    }

    public void alSeleccionarItem(String idAsignatura) {
        Intent intent = new Intent(this, DetalleAsignatura.class);
        intent.putExtra(FragmentoDetalleAsignatura.ID_ASIGNATURA, idAsignatura);
        startActivity(intent);
    }
}
