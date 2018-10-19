package br.com.desafios.charadequiz.Singleton;

import android.content.Context;

import java.util.List;

import br.com.desafios.charadequiz.Model.Quiz;
import br.com.desafios.charadequiz.Model.Usuario;
import br.com.desafios.charadequiz.preferences.UsuarioLoginPreferences;

public class DataStore {

    private static DataStore instance = null;

    //private List<Quiz> quizzes;

    private UsuarioLoginPreferences preferences;
    private Usuario usuario;
    private Context context;

    protected DataStore() {}

    public static DataStore sharedInstance() {

        if (instance == null)
            instance = new DataStore();

        return instance;
    }

    public void setContext(Context context) {

        this.context = context;
        preferences = new UsuarioLoginPreferences(context);

    }

    public UsuarioLoginPreferences getPreferences() {
        return preferences;
    }

    public Usuario userLogin(String login, String senha) {
        // Realiza Login
        return new Usuario();
    }

    public boolean userRegister(String name, String email, String celPhone, String password) {
        // Novo Usuário
        return false;
    }

    public Quiz newChallange(int idUsuario) {
        // Busca as perguntas do Quiz
        return new Quiz();
    }

    public Quiz saveResponses(Quiz quiz) {
        //Já retorna dados das estatisticas
        return new Quiz();
    }

    public Quiz generalResult(int id) {
        // Busca os dados
        return new Quiz();
    }



}
