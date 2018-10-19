package br.com.desafios.charadequiz.Singleton;

import android.content.Context;
import android.os.AsyncTask;

import java.net.HttpURLConnection;
import java.net.URL;

import android.net.Uri;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;


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

        new LoginUsuario(login,senha).execute("http://192.168.25.100/charadequizSlim/login/");
        return new Usuario();//userLogin.getUsuario();
    }

    public boolean userRegister(String name, String email, String celPhone, String password) {
        // Novo Usuário
        Integer result;
        result = ExecAddUsuario(GetJsonAddUsuario("http://192.168.25.100/charadequizSlim/registro/"));

        //Usuario novoUsuario = new Usuario(-1, name,email,celPhone,password);
        //new AddUsuario(novoUsuario).execute("http://192.168.25.100/charadequizSlim/registro/");
        if (result != -1)
            return true;
        else
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

    protected String GetJsonAddUsuario(String... urls) {

        String jsonStr = "";

        try {
            URL url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(45000);
            connection.setReadTimeout(30000);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            Uri.Builder builder = new Uri.Builder();

            builder.appendQueryParameter("Name", usuario.getName());
            builder.appendQueryParameter("Cellphone_Number", usuario.getCelphoneNumber());
            builder.appendQueryParameter("Email", usuario.getEmail());
            builder.appendQueryParameter("Password", usuario.getPassword());

            String qry = builder.build().getEncodedQuery();

            OutputStream outputStream = connection.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter writer = new BufferedWriter(outputStreamWriter);

            writer.write(qry);
            writer.flush();

            writer.close();
            outputStreamWriter.close();
            outputStream.close();

            connection.connect();

            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line = reader.readLine();
            while (line != null) {
                jsonStr += line;
                line = reader.readLine();
            }

            reader.close();
            inputStreamReader.close();
            inputStream.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonStr;
    }

    protected Integer ExecAddUsuario(String jsonStr) {
        try {
            JSONObject json = new JSONObject(jsonStr);
            /*if (json.getInt("id") != -1){
                usuario.setId(json.getInt("id"));
            }*/
            return json.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private class AddUsuario extends AsyncTask<String, Void, String> {

        private Usuario usuario;

        public AddUsuario(Usuario usuario) {

            this.usuario = usuario;
        }

        @Override
        protected String doInBackground(String... urls) {

            String jsonStr = "";

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(45000);
                connection.setReadTimeout(30000);
                connection.setRequestMethod("POST");
                connection.setDoInput(true);
                connection.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder();

                builder.appendQueryParameter("Name", usuario.getName());
                builder.appendQueryParameter("Cellphone_Number", usuario.getCelphoneNumber());
                builder.appendQueryParameter("Email", usuario.getEmail());
                builder.appendQueryParameter("Password", usuario.getPassword());

                String qry = builder.build().getEncodedQuery();

                OutputStream outputStream = connection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                BufferedWriter writer = new BufferedWriter(outputStreamWriter);

                writer.write(qry);
                writer.flush();

                writer.close();
                outputStreamWriter.close();
                outputStream.close();

                connection.connect();

                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);

                String line = reader.readLine();
                while (line != null) {
                    jsonStr += line;
                    line = reader.readLine();
                }

                reader.close();
                inputStreamReader.close();
                inputStream.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return jsonStr;
        }

        @Override
        protected void onPostExecute(String jsonStr) {
            super.onPostExecute(jsonStr);

            try {
                JSONObject json = new JSONObject(jsonStr);
                if (json.getInt("id") != -1){
                    usuario.setId(json.getInt("id"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class LoginUsuario extends AsyncTask<String, Void, String> {

        private String Email,Senha;


        public LoginUsuario(String Email, String Senha) {
            this.Email = Email;
            this.Senha = Senha;
        }

        @Override
        protected String doInBackground(String... urls) {

            String jsonStr = "";

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(45000);
                connection.setReadTimeout(30000);
                connection.setRequestMethod("POST");
                connection.setDoInput(true);
                connection.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder();

                builder.appendQueryParameter("Email", Email);
                builder.appendQueryParameter("Senha", Senha);

                String qry = builder.build().getEncodedQuery();

                OutputStream outputStream = connection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                BufferedWriter writer = new BufferedWriter(outputStreamWriter);

                writer.write(qry);
                writer.flush();

                writer.close();
                outputStreamWriter.close();
                outputStream.close();

                connection.connect();

                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);

                String line = reader.readLine();
                while (line != null) {
                    jsonStr += line;
                    line = reader.readLine();
                }

                reader.close();
                inputStreamReader.close();
                inputStream.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return jsonStr;
        }

        @Override
        protected void onPostExecute(String jsonStr) {
            super.onPostExecute(jsonStr);
            if(usuario == null) usuario = new Usuario();
            try {
                JSONObject json = new JSONObject(jsonStr);
                if (json.getInt("id") != -1){

                    usuario.setId(json.getInt("id"));
                    usuario.setName(json.getString("name"));
                    usuario.setCelphoneNumber(json.getString("cellphone_number"));
                    usuario.setEmail(json.getString("email"));
                    usuario.setPassword(json.getString("password"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    

}
