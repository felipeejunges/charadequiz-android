package br.com.desafios.charadequiz.Singleton;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import br.com.desafios.charadequiz.Dto.MainDto;
import br.com.desafios.charadequiz.Dto.ResumeDto;
import br.com.desafios.charadequiz.Model.Alternative;
import br.com.desafios.charadequiz.Model.Answer;
import br.com.desafios.charadequiz.Model.Question;
import br.com.desafios.charadequiz.Model.Quiz;
import br.com.desafios.charadequiz.Model.Usuario;
import br.com.desafios.charadequiz.preferences.UsuarioLoginPreferences;

public class DataStore {

    private static DataStore instance = null;

    //private List<Quiz> quizzes;
    private Quiz quiz;
    private List<Answer> answers;

    private UsuarioLoginPreferences preferences;
    private Usuario usuario;
    private Context context;

    private static String MeuIP = "http://fczcasa.ddns.net";

    private MainDto mainDto;

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

    public Usuario getUsuario(){
        return usuario;
    }

    public UsuarioLoginPreferences getPreferences() {
        return preferences;
    }

    public Usuario userLogin(String login, String senha) {
        Log.d("teste",login + " " + senha);
        /*String threadresult ="";
        try {
            threadresult =  new LoginUsuario(login,senha).execute(MeuIP + "/charadequizSlim/login/").get();
            Log.d("threadresult",threadresult);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Log.d("usuario",usuario.toString());

        return UsuarioFromJson(threadresult);//userLogin.getUsuario();
*/
       // Usuario nuser = ExecLoginUsuario(GetJsonLogin(MeuIP + "/charadequizSlim/login/",login,senha));
            //new LoginUsuario(login,senha).execute();
        //return new Usuario();//userLogin.getUsuario();
        //new LoginUsuario(login,senha).execute(MeuIP + "/charadequizSlim/login/");11111111111111111111111111111
        //return usuario;//userLogin.getUsuario();

        new LoginUsuario(login,senha).execute(MeuIP + "/charadequizSlim/login/");
//        Log.d("usuario_result",usuario.toString());
        return usuario;//userLogin.getUsuario();
    }

    public boolean userRegister(String name, String email, String celPhone, String password) {
        // Novo Usuário
        Integer result;
        //result = ExecAddUsuario(GetJsonAddUsuario(MeuIP + "/charadequizSlim/registro/"));

        Usuario novoUsuario = new Usuario(-1, name,email,celPhone,password);
        new AddUsuario(novoUsuario).execute(MeuIP + "/charadequizSlim/registro/");
//        if (result != -1)
//            return true;
//        else
            return false;
    }



    public Quiz saveResponses() {
        // Pega o anwer do datastore mesmo
        // E da um nullo no answer ou um new tanto faz
        //Mesma coisa pro Quiz
        return new Quiz();
    }

    public Quiz generalResult(int id) {
        // Busca os dados
        return new Quiz();
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Quiz newChallange(Integer usuarioId) {
        try {
            new GetNewQuiz(usuarioId).execute(MeuIP + "/charadequizSlim/getnewquiz/" + usuarioId).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        // while(!get.isCancelled()) {}
//        while(true) {
//            try {
//                get.get();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//            String getStatus = String.valueOf(get.getStatus());
//            String terminado = String.valueOf(Status.FINISHED);
//            if(terminado == getStatus || (quiz != null && quiz.getId() > 0)) {
//                break;
//            }
//        }
//        try {
//            quiz = new GetNewQuiz(usuarioId).execute(MeuIP + "/charadequizSlim/getnewquiz/" + usuarioId).get();
//            return getQuiz();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
        return getQuiz();
    }

    public ResumeDto pegarResumo() {
        new ResumeFinalDto(1).execute(MeuIP + "/charadequizSlim/resumoFinal/");

        //TODO: Alterar ID..
        return null;
    }

    public MainDto pegarMain() {
        new MainResume(getPreferences().getUsuarioId()).execute(MeuIP + "/charadequizSlim/resumoMain/");
        return null;
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

        private ProgressDialog dialog = new ProgressDialog(context);

        public LoginUsuario(String Email, String Senha) {
            this.Email = Email;
            this.Senha = Senha;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            this.dialog.setMessage("Realizando Login");
            this.dialog.show();
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

            try {
                JSONObject json = new JSONObject(jsonStr);
                if (json.getInt("id") != -1){
                    usuario = new Usuario();
                    usuario.setId(json.getInt("id"));
                    usuario.setName(json.getString("name"));
                    usuario.setCelphoneNumber(json.getString("cellphone_number"));
                    usuario.setEmail(json.getString("email"));
                    usuario.setPassword(json.getString("password"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            getPreferences().salvarUsuario(usuario);

            if (dialog.isShowing()) {
                dialog.dismiss();
            }

        }
    }

    private class MainResume extends AsyncTask<String, Void, String> {

        private MainDto mainresume;

        int userid;

        private ProgressDialog dialog = new ProgressDialog(context);

        public MainResume(int id) {
            this.userid = id;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            this.dialog.setMessage("Carregando Resumo Principal");
            this.dialog.show();
        }

        @Override
        protected String doInBackground(String... urls) {

            String jsonStr = "";

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(45000);
                connection.setReadTimeout(30000);
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder();
                builder.appendQueryParameter("userid", String.valueOf(userid));
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

                Log.d("JsonResultMainResume",jsonStr);

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
                    mainDto = new MainDto();
                    mainDto.setRespondidos( json.getInt("Total_Respondidos"));
                    mainDto.setTempoMedio(  json.getInt("Tempo_Medio"));
                    mainDto.setTempoTotal(  json.getInt("Tempo_Total"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            getPreferences().salvarUsuario(usuario);

            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    private class ResumeFinalDto extends AsyncTask<String, Void, String> {

        private ResumeDto resumeDto;

        int id;

        private ProgressDialog dialog = new ProgressDialog(context);

        public ResumeFinalDto(int id) {
            this.id = id;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            this.dialog.setMessage("Carregando Resumo Principal");
            this.dialog.show();
        }

        @Override
        protected String doInBackground(String... urls) {

            String jsonStr = "";

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(45000);
                connection.setReadTimeout(30000);
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder();
                builder.appendQueryParameter("id", String.valueOf(id));
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

                Log.d("JsonResultResumeDto",jsonStr);

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
                resumeDto = new ResumeDto();
                resumeDto.setAcertos    ( json.getInt("Total_Corretas"));
                resumeDto.setErros      ( json.getInt("Total_Erradas"));
                resumeDto.setEsperado   ( json.getInt("Tempo_Esperado"));
                resumeDto.setMedio      ( json.getInt("Tempo_Medio"));
                resumeDto.setTotal      ( json.getInt("Tempo_Total"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            getPreferences().salvarUsuario(usuario);

            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    private class GetNewQuiz extends AsyncTask<String, Void, String> {

       // private Quiz quiz;
        int userid;



        private ProgressDialog dialog = new ProgressDialog(context);

        public GetNewQuiz(int userid) {
            this.userid = userid;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            this.dialog.setMessage("Gerando novo quiz!");
            this.dialog.show();
        }

        @Override
        protected String doInBackground(String... urls) {

            String jsonStr = "";

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(45000);
                connection.setReadTimeout(30000);
                connection.setRequestMethod("GET");
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

                Log.d("JsonResultGetNewQuiz",jsonStr);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            quiz = new Quiz();
            try {
                JSONObject json = new JSONObject(jsonStr);

                Log.d("JsonResultGetNewQuiz",jsonStr);
                quiz.setId              ( json.getInt("idQuiz"));
                //quiz.setAnswers              ( json.getInt("QuestionID"));
                //TODO: Criar Answer no WebService.

                List<Question> questions = new ArrayList<>();

                JSONArray c = json.getJSONArray("questions");
                for (int i = 0 ; i < c.length(); i++)
                {
                    // Para fins de testes e academicos
                    if(i == 5) {
                        break;
                    }

                    JSONObject obj = c.getJSONObject(i);
                    Question _quest = new Question();
                    _quest.setId(obj.getInt("QuestionID"));
                    _quest.setDescription(obj.getString("QuestionDescription"));
                    _quest.setMaxtime(obj.getInt("QuestionMaxTime"));


                    List<Alternative> alternatives = new ArrayList<>();
                    JSONArray d = obj.getJSONArray("Alternatives");
                    for (int z = 0 ; z < d.length(); z++)
                    {
                        JSONObject objalt = d.getJSONObject(z);
                        Alternative _alt = new Alternative();

                        _alt.setId(objalt.getInt("id"));
                        _alt.setAnswer(objalt.getString("answer"));
                     //   _alt.setCorrect(objalt.getBoolean("correct"));
                      //  _alt.setUnactive(objalt.getBoolean("inactive"));

                        alternatives.add(_alt);
                    }
                    _quest.setAlternatives(alternatives);
                    questions.add(_quest);

                }

                quiz.setQuestions(questions);
            } catch (JSONException e) {
                Log.d("jsonStr", jsonStr);
                e.printStackTrace();
            }

            return jsonStr;
        }

        @Override
        protected void onPostExecute(String jsonStr) {
            super.onPostExecute(jsonStr);


            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

}
