package br.com.desafios.charadequiz.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;


    private int id;
    private String name;
    private String email;
    private String celphoneNumber;

    private String password;


    List<Quiz> quizzes = new ArrayList<>();

    private Set<Integer> perfis = new HashSet<>();

    public Usuario(){
    }

    public Usuario(int id, String name, String email, String celphoneNumber, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.celphoneNumber = celphoneNumber;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelphoneNumber() {
        return celphoneNumber;
    }

    public void setCelphoneNumber(String celphoneNumber) {
        this.celphoneNumber = celphoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

}

