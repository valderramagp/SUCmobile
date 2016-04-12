package suc.itmotions.net;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import layout.FragmentoDetalleAsignatura;

public class DetalleAsignaturas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_asignaturas);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detalle);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();

            arguments.putString(FragmentoDetalleAsignatura.ID_ASIGNATURA,
                    getIntent().getStringExtra(FragmentoDetalleAsignatura.ID_ASIGNATURA));

            FragmentoDetalleAsignatura fragment = new FragmentoDetalleAsignatura();

            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction().add(R.id.contenedor_detalle, fragment).commit();


        }
    }
}
