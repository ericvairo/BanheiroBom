package com.example.eric.banheirobomv1;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;


public class MainActivity extends Activity {
    private Button btLogin;
    private Button btLogout;
    private ListView lvUsuarios;
    private Button btAbreCadastro;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btLogin = (Button) findViewById(R.id.login);
        btLogout = (Button) findViewById(R.id.logout);
        btAbreCadastro =(Button) findViewById(R.id.btCadastrarNovo);
        lvUsuarios = (ListView) findViewById(R.id.lvNome);

        btAbreCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(it);
            }
        });


        if(Build.VERSION.SDK_INT>9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        // boolean resultado = dao.inserirUsuario(new Usuario(3,"camposferreira.anna@gmail.com", "camposferreira", "flordelis"));
        //Log.d("ExemploWS",resultado+"");
//        loginFace(null);
    }

    @Override
    protected void onResume(){

        super.onResume();
        UsuarioDAO dao = new UsuarioDAO();
        ArrayList<Usuario> lista = dao.buscarTodosUsuarios();
        Log.d("ExemploWS", lista.toString());

        ArrayAdapter<Usuario> adpUsr = new ArrayAdapter<Usuario>(this,android.R.layout.simple_list_item_1, lista);

        lvUsuarios.setAdapter(adpUsr);



    }


    public void loginFace(View view){
        Session.openActiveSession(this, true, new Session.StatusCallback() {

            // callback when session changes state
            @Override
            public void call(Session session, SessionState state, Exception exception) {
                if (session.isOpened()) {

                    // make request to the /me API
                    Request.newMeRequest(session, new Request.GraphUserCallback() {

                        // callback after Graph API response with user object
                        @Override
                        public void onCompleted(GraphUser user, Response response) {
                            if (user != null) {
                                Log.i("Script", "Name: " + user.getName());

                                btLogin.setVisibility(View.GONE);
                                btLogout.setVisibility(View.VISIBLE);
                                lvUsuarios.setVisibility(View.VISIBLE);
                                btAbreCadastro.setVisibility(View.VISIBLE);
                                findViewById(R.id.BemVindo).setVisibility(View.GONE);



                            }
                        }
                    }).executeAsync();
                }
            }
        });
    }


    public void logoutFace(View view){
        if(Session.getActiveSession() != null){
            Session.getActiveSession().closeAndClearTokenInformation();
            Session.setActiveSession(null);

            btLogin.setVisibility(View.VISIBLE);
            btLogout.setVisibility(View.GONE);
            lvUsuarios.setVisibility(View.GONE);
            btAbreCadastro.setVisibility(View.GONE);
            findViewById(R.id.BemVindo).setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
    }
}
