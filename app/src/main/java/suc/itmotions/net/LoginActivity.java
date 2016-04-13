package suc.itmotions.net;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import suc.itmotions.net.webservice.Constantes;

public class LoginActivity extends AppCompatActivity {
    Button controlerbtnLogin;
    EditText editUser, editPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences prefs = getSharedPreferences("user", Context.MODE_PRIVATE);
        boolean login = prefs.getBoolean("login", false);
        if (login) {
            Intent intent = new Intent(LoginActivity.this, ListaAsignaturas.class);
            startActivity(intent);
        }

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        controlerbtnLogin = (Button)findViewById(R.id.btnLogin);
        editUser = (EditText) findViewById(R.id.editUser);
        editPass = (EditText) findViewById(R.id.editPass);

        controlerbtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = attemptLogin();

                if (status.equals("1"))
                {


                    SharedPreferences prefs = getSharedPreferences("user", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("name", editUser.getText().toString());
                    editor.putString("password", editPass.getText().toString());
                    editor.putBoolean("login", true);

                    editor.apply();

                    Intent intent = new Intent(LoginActivity.this, ListaAsignaturas.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Por favor verifique sus credenciales", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String attemptLogin() {
        JSONObject jsonObject;
        String line;
        String status = "3";

        try {
            URL link = new URL(Constantes.IP + Constantes.MODULE_CREDENTIALS + "login.php" + "?username=" + editUser.getText().toString() + "&password=" + editPass.getText().toString());
            HttpURLConnection connection = (HttpURLConnection) link.openConnection();

            StringBuilder result = new StringBuilder();
            InputStream inputStream = new BufferedInputStream(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            while((line = bufferedReader.readLine()) != null)
            {
                result.append(line);
            }

            jsonObject = new JSONObject(result.toString());

            status = jsonObject.getString("status");

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return status;
    }
}
