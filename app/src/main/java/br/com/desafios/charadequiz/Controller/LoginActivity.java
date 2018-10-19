package br.com.desafios.charadequiz.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.desafios.charadequiz.Model.Usuario;
import br.com.desafios.charadequiz.R;
import br.com.desafios.charadequiz.Singleton.DataStore;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DataStore.sharedInstance().setContext(this);
    }

    public void login() {
        // Pegar dados do form
        DataStore.sharedInstance().getPreferences().salvarUsuario(
                DataStore.sharedInstance().userLogin("login", "123")
        );

    }
}
