package br.com.desafios.charadequiz.Controller;

import android.service.autofill.Dataset;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.desafios.charadequiz.R;
import br.com.desafios.charadequiz.Singleton.DataStore;
import br.com.desafios.charadequiz.preferences.UsuarioLoginPreferences;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataStore.sharedInstance().setContext(this);

        DataStore.sharedInstance().getPreferences().naoEstaLogado();

        // Toda a logica
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
