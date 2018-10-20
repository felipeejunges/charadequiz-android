package br.com.desafios.charadequiz.Controller;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import br.com.desafios.charadequiz.Model.Alternative;
import br.com.desafios.charadequiz.Model.Question;
import br.com.desafios.charadequiz.R;

public class QuestionFragment extends Fragment {

    private TextView txtQuestion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_question, container, false);
        carregaCampos(rootView);
        pegaPergunta();
        return rootView;
    }

    private void carregaCampos(ViewGroup v) {
        txtQuestion = v.findViewById(R.id.txtQuestion_Quiz);
    }

    private void pegaPergunta() {
        
        Bundle parametros = getArguments();
        if(parametros != null) {
            Question question = (Question) parametros.getSerializable("question");
            popularCampos(question);
        }
        
    }

    private void popularCampos(Question question) {
        StringBuilder builder = new StringBuilder();
        builder.append(question.getDescription());
        int i = 0;
        for(Alternative a : question.getAlternatives()) {
            builder.append(
                    verificaLetra(i) + ") " +
                    a.getAnswer()
            );
            i++;
        }
        txtQuestion.setText(builder.toString());
    }

    private String verificaLetra(int i) {
        return String.valueOf(
                (char) 97 + i
        );
    }
}
