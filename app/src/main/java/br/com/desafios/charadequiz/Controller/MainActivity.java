package br.com.desafios.charadequiz.Controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import br.com.desafios.charadequiz.Dto.MainDto;
import br.com.desafios.charadequiz.R;
import br.com.desafios.charadequiz.Singleton.DataStore;

public class MainActivity extends AppCompatActivity {

    TextView tvRespondidos;
    TextView tvTotal;
    TextView tvMedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DataStore.sharedInstance().setContext(this);
        DataStore.sharedInstance().getPreferences().naoEstaLogado();

        tvRespondidos = findViewById(R.id.txtQntRespondidos_Main);
        tvTotal = findViewById(R.id.txtTempoTotal_Main);
        tvMedia = findViewById(R.id.txtTempoMedio_Main);

        // Toda a logica

//        MainDto main = DataStore.sharedInstance().pegarMain();
//
//        tvRespondidos.setText(main.getRespondidos());
//        tvTotal.setText(new SimpleDateFormat("hh:mm:ss").format(new Timestamp(main.getTempoTotal())));
//        tvTotal.setText(new SimpleDateFormat("hh:mm:ss").format(new Timestamp(main.getTempoMedio())));

    }

    public void onClickIWantAChallange(View v) {
        Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menuLogout:
                AlertDialog.Builder message = new AlertDialog.Builder(this);
                message.setTitle("Tem certeza que deseja realizar logout?");
                message.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DataStore.sharedInstance().getPreferences().deslogar();
                    }
                });
                message.setNegativeButton("Não", null);
                message.show();
                break;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
