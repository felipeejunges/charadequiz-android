package br.com.desafios.charadequiz.Controller;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.desafios.charadequiz.Adapters.CustomViewPager;
import br.com.desafios.charadequiz.Adapters.ScreenSlidePagerAdapter;
import br.com.desafios.charadequiz.Model.Alternative;
import br.com.desafios.charadequiz.Model.Answer;
import br.com.desafios.charadequiz.Model.Question;
import br.com.desafios.charadequiz.Model.Quiz;
import br.com.desafios.charadequiz.R;
import br.com.desafios.charadequiz.Singleton.DataStore;

public class QuestionActivity extends AppCompatActivity {

    private CustomViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private List<Fragment> fragments;
    private QuestionFragment questionFragment;
    private TextView tvRespondidos;
    private TextView tvTotal;
    private TextView tvAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        mPager = findViewById(R.id.cardView);
        fragments = new ArrayList<>();



        DataStore.sharedInstance().setContext(this);
        Quiz quiz = DataStore.sharedInstance().newChallange(
                DataStore.sharedInstance().getPreferences().getUsuarioId()
        );

        DataStore.sharedInstance().setQuiz(quiz);
        DataStore.sharedInstance().setAnswers(new ArrayList<Answer>());

        mPagerAdapter = new ScreenSlidePagerAdapter(this, getSupportFragmentManager(), fragments);
        mPager.setPagingEnabled(false);
        mPager.setAdapter(mPagerAdapter);

        initializer();
        tvRespondidos.setText(0);

        for (Question q: quiz.getQuestions()) {
            fragments.add(questionFragment = new QuestionFragment());
            commitToFragment(getSupportFragmentManager(),questionFragment, q);
        }
    }

    private void initializer() {
        tvRespondidos = findViewById(R.id.txtQntRespondidos_Quiz);
        tvTotal = findViewById(R.id.txtTempoTotal_Quiz);
        tvAtual = findViewById(R.id.txtTempoMedio_Quiz);

    }

    private void commitToFragment(FragmentManager manager, Fragment fragmento, Question q) {
        android.support.v4.app.FragmentTransaction tx = manager.beginTransaction();
        Bundle parametros = new Bundle();
        parametros.putSerializable("question", q);
        fragmento.setArguments(parametros);

        tx.commit();
    }

    private void passarResposta(int i, View v) {
        int qntRespondidos = (Integer.parseInt(
                String.valueOf(tvRespondidos.getText())));

        Quiz quiz = DataStore.sharedInstance().getQuiz();
        Alternative alternative =
                quiz.getQuestions().get(qntRespondidos)
                .getAlternatives().get(i);
        Timestamp timestamp = stringToHour(String.valueOf(tvAtual.getText()));
        long tempo = 0;
        if(timestamp != null) {
            tempo = timestamp.getTime();
        }
       DataStore.sharedInstance().getAnswers().add(new Answer(0, tempo, alternative, quiz));
        qntRespondidos++;

        tvAtual.setText(0);

        if(qntRespondidos == fragments.size()) {
            DataStore.sharedInstance().saveResponses();
        }

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder message = new AlertDialog.Builder(this);
        message.setTitle("Tem certeza que deseja sair do Quiz?");
        message.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent irTelaMain = new Intent(QuestionActivity.this, MainActivity.class);
                irTelaMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(irTelaMain);
                finish();
            }
        });
        message.setNegativeButton("Não", null);
        message.show();
    }

    public Timestamp stringToHour(String str) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        try {

            Date parsedDate = dateFormat.parse(str);
            return new java.sql.Timestamp(parsedDate.getTime());
        } catch(Exception e) { //this generic but you can control another types of exception

            return null;
        }

    }

    public void onRespostaA(View v) {
        passarResposta(0, v);
    }

    public void onRespostaB(View v) {
        passarResposta(1, v);
    }

    public void onRespostaC(View v) {
        passarResposta(2, v);
    }

    public void onRespostaD(View v) {
        passarResposta(3, v);
    }
}
