package suc.itmotions.net.entities;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gusta on 11/04/2016.
 */
public class Asignatura {
    private String id,profesor,materia;

    public Asignatura(JSONObject asignatura){
        try {
            setId(asignatura.getString("id"));
            setMateria(asignatura.getString("materia"));
            setProfesor(asignatura.getString("profesor"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }
}
