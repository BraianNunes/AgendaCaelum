package br.com.caelum.cadastro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by Braian Nunes on 8/16/2015.
 */
public class Formulario extends Activity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario);

        final Intent intent = getIntent();
        final Aluno alunoParaSerAlterado = (Aluno) intent.getSerializableExtra("alunoSelecionado");

        helper = new FormularioHelper(this);

        final Button gravar = (Button) findViewById(R.id.bt_gravar);
        if (alunoParaSerAlterado != null) {
            gravar.setText("Alterar");
            helper.colocaAlunoNoFormulario(alunoParaSerAlterado);
        }

        gravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aluno aluno = helper.obterAlunoDoFormulario();
                AlunoDAO dao = new AlunoDAO(Formulario.this);
                dao.salva(aluno);
                dao.close();

                finish(); // Botao voltar
            }
        });
    }
}
