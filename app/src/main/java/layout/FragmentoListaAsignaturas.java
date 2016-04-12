package layout;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
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
import suc.itmotions.net.adapters.AdaptadorAsignaturas;
import suc.itmotions.net.entities.Asignatura;
import suc.itmotions.net.webservice.Constantes;

public class FragmentoListaAsignaturas extends Fragment {

    private AdaptadorAsignaturas adapter;

    public FragmentoListaAsignaturas() {

    }

    public static FragmentoListaAsignaturas crear() {
        return new FragmentoListaAsignaturas();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmento_lista_asignaturas, container, false);
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.Rv);
        List<Asignatura> asignaturas = consultaAsignaturas();
        adapter = new AdaptadorAsignaturas(asignaturas, getActivity());
        return v;
    }

    public List<Asignatura> consultaAsignaturas() {
        JSONObject jsonObject = null;
        String line;

        List<Asignatura> Asignaturas = null;
        try {
            SharedPreferences preferencias = this.getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
            String username = preferencias.getString("name","0");
            URL link = new URL(Constantes.IP + Constantes.MODULE_ALUMNOS + "getAsignaturas.php" + "?username=" + username);
            HttpURLConnection connection = (HttpURLConnection) link.openConnection();

            StringBuilder result = new StringBuilder();
            InputStream inputStream = new BufferedInputStream(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }

            jsonObject = new JSONObject(result.toString());

            if (jsonObject != null) {
                Asignaturas = new ArrayList<Asignatura>();

                JSONArray jsonArray = jsonObject.optJSONArray("asignaturas");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject asignatura = jsonArray.getJSONObject(i);
                    Asignatura as = new Asignatura(asignatura);
                    Asignaturas.add(as);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Asignaturas;
    }
}
