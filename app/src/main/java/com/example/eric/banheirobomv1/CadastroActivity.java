package com.example.eric.banheirobomv1;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by eric on 22/07/2015.
 */
public class CadastroActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);

        final EditText edNome =(EditText) findViewById(R.id.etNome);
        final EditText edEmail =(EditText) findViewById(R.id.etEmail);
        final EditText edSenha =(EditText) findViewById(R.id.etSenha);
        Button btCadastrar =(Button) findViewById(R.id.btCadastrar);


        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UsuarioDAO dao = new UsuarioDAO();
                boolean resultado = dao.inserirUsuario(new Usuario(3,edEmail.getText().toString(),
                        edNome.getText().toString(), edSenha.getText().toString()));

                if(resultado){
                    finish();

                }else{
                    Toast.makeText(CadastroActivity.this, "Erro no Cadastro", Toast.LENGTH_LONG).show();
                }
        }
    });
    }
}
