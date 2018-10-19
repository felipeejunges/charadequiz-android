package br.com.desafios.charadequiz.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Quiz implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private boolean unactive;

    private List<Answer> answers = new ArrayList<>();
    
    private List<Question> questions = new ArrayList<>();
    private Usuario user;

    public Quiz() {
    }

    public Quiz(int id, boolean unactive, Usuario user) {
        this.id = id;
        this.unactive = unactive;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isUnactive() {
        return unactive;
    }

    public void setUnactive(boolean unactive) {
        this.unactive = unactive;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
}
