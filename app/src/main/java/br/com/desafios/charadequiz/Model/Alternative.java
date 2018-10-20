package br.com.desafios.charadequiz.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Alternative implements Serializable {
    private static final long serialVersionUID = 1L;


    private int id;
    private String answer;
    private boolean correct;
    private boolean unactive;

    private Question question;

    private List<Answer> answers = new ArrayList<>();

    public Alternative() {
    }

    public Alternative(int id) {
        this.id = id;
    }

    public Alternative(String answer, boolean correct, boolean unactive, Question question) {
        this.answer = answer;
        this.correct = correct;
        this.unactive = unactive;
        this.question = question;
    }

    public Alternative(int id, String answer, boolean correct) {
        this.id = id;
        this.answer = answer;
        this.correct = correct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public boolean isUnactive() {
        return unactive;
    }

    public void setUnactive(boolean unactive) {
        this.unactive = unactive;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
