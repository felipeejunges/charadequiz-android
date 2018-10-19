package br.com.desafios.charadequiz.preferences;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import br.com.desafios.charadequiz.Controller.LoginActivity;
import br.com.desafios.charadequiz.Controller.MainActivity;
import br.com.desafios.charadequiz.Model.Usuario;

/**
 * Created by Junges on 12/11/2017.
 */

public class UsuarioLoginPreferences {
    private static final String USUARIO_PREFERENCES = "br.com.desafios.charadequiz.preferences.UsuarioLoginPreferences";
    private static final String ID_USUARIO = "id_usuario";
    private static final String NAME_USUARIO = "name_usuario";
    private static final String EMAIL_USUARIO = "email_usuario";
    private static final String CelphoneNumber_USUARIO = "celphoneNumber_usuario";
    private Context context;

    public UsuarioLoginPreferences(Context context) {

        this.context = context;
    }

    public void salvarUsuario(Usuario usuario) {
        SharedPreferences.Editor editor = getEditor();
        editor.putInt(ID_USUARIO, usuario.getId());
        editor.putString(NAME_USUARIO, usuario.getName());
        editor.putString(EMAIL_USUARIO, usuario.getEmail());
        editor.putString(CelphoneNumber_USUARIO, usuario.getCelphoneNumber());
        editor.commit();

        Log.d("usuario",usuario.toString());

        Intent irTelaMain = new Intent(context, MainActivity.class);
        irTelaMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        context.startActivity(irTelaMain);
        ((Activity) context).finish();

    }

    private SharedPreferences.Editor getEditor() {
        SharedPreferences preferences = getSharedPreferences();
        return preferences.edit();
    }

    private SharedPreferences getSharedPreferences() {
        return context.getSharedPreferences(USUARIO_PREFERENCES, context.MODE_PRIVATE);
    }

    public Integer getUsuarioId() {
        SharedPreferences preferences = getSharedPreferences();
        return preferences.getInt(ID_USUARIO, 0);
    }

    public Usuario getUsuario() {
        SharedPreferences preferences = getSharedPreferences();

        Usuario usuario = new Usuario();
        usuario.setId(preferences.getInt(ID_USUARIO, 0));
        usuario.setName(preferences.getString(NAME_USUARIO, ""));
        usuario.setEmail(preferences.getString(EMAIL_USUARIO, ""));
        usuario.setCelphoneNumber(preferences.getString(CelphoneNumber_USUARIO, ""));

        return usuario;
    }

    public boolean estaLogado() {
        return getUsuarioId() != 0;
    }

    public void naoEstaLogado() {
        if(!estaLogado()) {
            Intent irTelaDeLogin = new Intent(context, LoginActivity.class);
            irTelaDeLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
            context.startActivity(irTelaDeLogin);
            ((Activity) context).finish();
        }
    }

    public void deslogar() {
        SharedPreferences.Editor editor = getEditor();
        editor.clear();
        editor.commit();
        naoEstaLogado();
    }
}
