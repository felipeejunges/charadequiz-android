package br.com.desafios.charadequiz.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import br.com.desafios.charadequiz.Model.Usuario;
import br.com.desafios.charadequiz.R;
import br.com.desafios.charadequiz.Singleton.DataStore;

public class LoginActivity extends Activity {

    EditText txtLogin_Login;
    EditText txtPassword_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        txtLogin_Login        = findViewById(R.id.txtLogin_Login);
        txtPassword_Login       = findViewById(R.id.txtPassword_Login);

        DataStore.sharedInstance().setContext(this);
    }

    public void btnCriarContaOnClick(View view){
        Intent irTelaCadastro = new Intent(this, RegisterActivity.class);
        this.startActivity(irTelaCadastro);
    }

    public void btnLoginOnClick(View view){
        DataStore.sharedInstance().getPreferences().salvarUsuario(
                DataStore.sharedInstance().userLogin(txtLogin_Login.getText().toString(),
                                                        txtPassword_Login.getText().toString())
        );

    }
}
