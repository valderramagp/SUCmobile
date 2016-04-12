package layout;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import suc.itmotions.net.R;
import suc.itmotions.net.adapters.DetalleAsignaturaAdapter;
import suc.itmotions.net.entities.DetalleAsignatura;
import suc.itmotions.net.webservice.Constantes;

public class FragmentoDetalleAsignatura extends Fragment {

    public static final String EXTRA_ID = "IDASIGNATURA";

    private String extra;

    private List<DetalleAsignatura> detalleAsignaturaList;

    private String asignatura;


    public FragmentoDetalleAsignatura(){}

    public static FragmentoDetalleAsignatura createInstance(String idAsignatura) {
        FragmentoDetalleAsignatura detailFragment = new FragmentoDetalleAsignatura();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_ID, idAsignatura);
        detailFragment.setArguments(bundle);
        return detailFragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceStatte) {

        View v = inflater.inflate(R.layout.fragmento_detalle_asignatura, container, false);

        View recyclerView = v.findViewById(R.id.recycler_detalle);

        assert recyclerView != null;

        extra = getArguments().getString(EXTRA_ID);

        detalleAsignaturaList = getDetalleAsignatura();

        prepararLista((RecyclerView) recyclerView, detalleAsignaturaList);

        if (asignatura != null) {
            Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar_detalle);
            if (toolbar != null) {
               // toolbar.inflateMenu();
            }

        }
        return v;
    }

    public void prepararLista(@NonNull RecyclerView recyclerView, List<DetalleAsignatura> asignaturas
    ) {
        recyclerView.setAdapter(new DetalleAsignaturaAdapter(asignaturas));
    }

    public List<DetalleAsignatura> getDetalleAsignatura() {
        JSONObject jsonObject = null;

        String line;

        detalleAsignaturaList = null;

        try {
            SharedPreferences preferencias = this.getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
            String username = preferencias.getString("name","0");
            URL link = new URL(Constantes.IP + Constantes.MODULE_ALUMNOS + "getAsignaturaDetalle.php" + "?username=" + username + "&asignatura=" + extra);
            HttpURLConnection connection = (HttpURLConnection) link.openConnection();

            StringBuilder result = new StringBuilder();
            InputStream inputStream = new BufferedInputStream(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }

            jsonObject = new JSONObject(result.toString());

            if (jsonObject != null) {
                detalleAsignaturaList = new ArrayList<>();

                JSONArray jsonArray = jsonObject.optJSONArray("asignaturas");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject asObject = jsonArray.getJSONObject(i);
                    DetalleAsignatura asign = new DetalleAsignatura(asObject);
                    detalleAsignaturaList.add(asign);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return detalleAsignaturaList;
    }

}
