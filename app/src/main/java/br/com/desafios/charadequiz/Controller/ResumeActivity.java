package br.com.desafios.charadequiz.Controller;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import br.com.desafios.charadequiz.Dto.ResumeDto;
import br.com.desafios.charadequiz.R;
import br.com.desafios.charadequiz.Singleton.DataStore;

public class ResumeActivity extends Activity {

    TextView txtTempoEsperado_Resume;
    TextView    txtTempoTotal_Resume;
    TextView txtTempoMedio_Resume;
    TextView    txtAcertos_Resume;
    TextView txtErros_Resume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

        txtTempoEsperado_Resume = findViewById(R.id.txtTempoEsperado_Resume);
        txtTempoTotal_Resume = findViewById(R.id.txtTempoTotal_Resume);
        txtTempoMedio_Resume = findViewById(R.id.txtTempoMedio_Resume);
        txtAcertos_Resume = findViewById(R.id.txtAcertos_Resume);
        txtErros_Resume = findViewById(R.id.txtErros_Resume);

        ResumeDto resume = DataStore.sharedInstance().pegarResumo();

    }

    public void onBtnVoltar(View v) {
        Intent intent = new Intent(ResumeActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
       finish();
    }
}
