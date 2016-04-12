package suc.itmotions.net;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import layout.FragmentoDetalleAsignatura;
import suc.itmotions.net.entities.DetalleAsignatura;
import suc.itmotions.net.webservice.Constantes;

public class DetalleAsignaturas extends AppCompatActivity {

    private String idAsignatura;

    public static void launch(Activity activity, String idAsignatura) {
        Intent intent = getLaunchIntent(activity, idAsignatura);
    }

    public static Intent getLaunchIntent(Context context, String idAsignatura) {
        Intent intent = new Intent(context, DetalleAsignatura.class);
        intent.putExtra(Constantes.EXTRA_ID, idAsignatura);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_asignaturas);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detalle);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (getIntent().getStringExtra(Constantes.EXTRA_ID) != null) {
            idAsignatura = getIntent().getStringExtra(Constantes.EXTRA_ID);
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.contenedor_detalle,
                    FragmentoDetalleAsignatura.createInstance(idAsignatura), "FragmentoDetalleAsignatura").commit();
        }
    }
}
