package suc.itmotions.net.entities;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gusta on 12/04/2016.
 */
public class DetalleAsignatura {

    private String unidad, saber, ser, hacer, asistencia, calFinal;

    public DetalleAsignatura(JSONObject asignatura) {
        try {
            setUnidad(asignatura.getString("unidad"));
            setSaber(asignatura.getString("saber") + "%");
            setSer(asignatura.getString("ser") + "%");
            setHacer(asignatura.getString("hacer") + "%");
            setAsistencia(asignatura.getString("asistencia") + "%");
            setCalFinal(asignatura.getString("final") + "%");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getSaber() {
        return saber;
    }

    public void setSaber(String saber) {
        this.saber = saber;
    }

    public String getSer() {
        return ser;
    }

    public void setSer(String ser) {
        this.ser = ser;
    }

    public String getHacer() {
        return hacer;
    }

    public void setHacer(String hacer) {
        this.hacer = hacer;
    }

    public String getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }

    public String getCalFinal() {
        return calFinal;
    }

    public void setCalFinal(String calFinal) {
        this.calFinal = calFinal;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
}
