package br.com.desafios.charadequiz.Model;

import java.io.Serializable;


public class Answer implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private boolean unactive;
    private long time;

    private Alternative alternative;
    private Quiz quiz;

    //private Question question;


    public Answer() {
    }

    public Answer(int id, boolean unactive, long time, Alternative alternative, Quiz quiz) {
        this.id = id;
        this.unactive = unactive;
        this.time = time;
        this.alternative = alternative;
        this.quiz = quiz;
    }

    public Answer(int id, long time, Alternative alternative, Quiz quiz) {
        this.id = id;
        this.time = time;
        this.alternative = alternative;
        this.quiz = quiz;
    }

    public Answer(boolean unactive, long time, Alternative alternative, Quiz quiz) {
        this.unactive = unactive;
        this.time = time;
        this.alternative = alternative;
        this.quiz = quiz;
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Alternative getAlternative() {
        return alternative;
    }

    public void setAlternative(Alternative alternative) {
        this.alternative = alternative;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
