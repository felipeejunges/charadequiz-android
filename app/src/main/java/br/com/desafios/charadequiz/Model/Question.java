package br.com.desafios.charadequiz.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Question implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String description;
    private boolean unactive;
    private long maxtime;


    private List<Alternative> alternatives = new ArrayList<>();


    private List<Quiz> quizzes = new ArrayList<>();

    public Question() {
    }

    public Question(int id, String description, boolean unactive, long maxtime) {
        this.id = id;
        this.description = description;
        this.unactive = unactive;
        this.maxtime = maxtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isUnactive() {
        return unactive;
    }

    public void setUnactive(boolean unactive) {
        this.unactive = unactive;
    }

    public long getMaxtime() {
        return maxtime;
    }

    public void setMaxtime(long maxtime) {
        this.maxtime = maxtime;
    }

    public List<Alternative> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<Alternative> alternatives) {
        this.alternatives = alternatives;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }
}
