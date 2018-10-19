package br.com.desafios.charadequiz.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import br.com.desafios.charadequiz.R;
import br.com.desafios.charadequiz.Singleton.DataStore;

public class RegisterActivity extends AppCompatActivity {

    EditText txtNome_Register;
    EditText txtEmail_Register;
    EditText txtTelefone_Register;
    EditText txtPassword_Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        txtNome_Register        = findViewById(R.id.txtNome_Register);
        txtEmail_Register       = findViewById(R.id.txtEmail_Register);
        txtTelefone_Register    = findViewById(R.id.txtTelefone_Register);
        txtPassword_Register    = findViewById(R.id.txtPassword_Register);



        DataStore.sharedInstance().setContext(this);
    }

    public void btnCadastroOnClick(View view){
        DataStore.sharedInstance().userRegister(
                                                txtNome_Register.getText().toString(),
                                                txtEmail_Register.getText().toString(),
                                                txtTelefone_Register.getText().toString(),
                                                txtPassword_Register.getText().toString());

        this.finish();
    }

}
