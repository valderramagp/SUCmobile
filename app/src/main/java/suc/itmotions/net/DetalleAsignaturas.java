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
        activity.startActivityForResult(intent, Constantes.CODIGO_DETALLE);
    }

    public static Intent getLaunchIntent(Context context, String idAsignatura) {
        Intent intent = new Intent(context, DetalleAsignaturas.class);
        intent.putExtra(Constantes.EXTRA_ID, idAsignatura);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_asignaturas);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (getIntent().getStringExtra(Constantes.EXTRA_ID) != null) {
            idAsignatura = getIntent().getStringExtra(Constantes.EXTRA_ID);
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.contenedor,
                    FragmentoDetalleAsignatura.createInstance(idAsignatura), "FragmentoDetalleAsignatura").commit();
        }
    }
}
