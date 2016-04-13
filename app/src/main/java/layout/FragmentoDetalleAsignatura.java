package layout;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nispok.snackbar.Snackbar;

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
import suc.itmotions.net.adapters.AdaptadorAsignaturas;
import suc.itmotions.net.adapters.DetalleAsignaturaAdapter;
import suc.itmotions.net.entities.DetalleAsignatura;
import suc.itmotions.net.webservice.Constantes;

public class FragmentoDetalleAsignatura extends Fragment {

    public static final String EXTRA_ID = "IDASIGNATURA";

    private String extra;

    private List<DetalleAsignatura> detalleAsignaturaList;

    private String asignatura;

    private DetalleAsignaturaAdapter adapter;


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

        extra = getArguments().getString(EXTRA_ID);

        detalleAsignaturaList = getDetalleAsignatura();

        if (detalleAsignaturaList != null) {
            RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recycler_detalle);
            adapter = new DetalleAsignaturaAdapter(detalleAsignaturaList);
            recyclerView.setAdapter(adapter);
        }
        return v;
    }

    public List<DetalleAsignatura> getDetalleAsignatura() {
        JSONObject jsonObject = null;

        String line;

        List<DetalleAsignatura> detalleAsignaturaList = null;

        try {
            SharedPreferences preferencias = this.getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
            String username = preferencias.getString("name","0");
            URL link = new URL(Constantes.IP + Constantes.MODULE_ASIGNATURAS + "getDetail.php" + "?user=" + username + "&asignatura=" + extra);
            HttpURLConnection connection = (HttpURLConnection) link.openConnection();
            connection.setUseCaches(false);
            connection.setAllowUserInteraction(false);
            connection.addRequestProperty("Authorization", "Basic YWRtaW4fYFgjkl5463");
            connection.setRequestMethod("GET");
            StringBuilder result2 = new StringBuilder();
            InputStream inputStream = new BufferedInputStream(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            while ((line = bufferedReader.readLine()) != null) {
                result2.append(line);
            }
            if (result2 != null) {

                jsonObject = new JSONObject(result2.toString());
                if (procesarRespuesta(jsonObject)) {
                    detalleAsignaturaList = new ArrayList<>();
                    JSONArray jsonArray = jsonObject.optJSONArray("detalle");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject asObject = jsonArray.getJSONObject(i);
                        DetalleAsignatura asign = new DetalleAsignatura(asObject);
                        detalleAsignaturaList.add(asign);
                    }
                } else {
                    Toast.makeText(getActivity(), "Aún no suben calificaciones!", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return detalleAsignaturaList;
    }

    private boolean procesarRespuesta(JSONObject response) {
        boolean validacion = false;
        try {
            String estado = response.getString("status");
            validacion = estado.equals("1") ? true : false;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return validacion;
    }
}
