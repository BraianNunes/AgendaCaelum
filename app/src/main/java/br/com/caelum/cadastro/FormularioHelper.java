package br.com.caelum.cadastro;

import android.util.Log;
import android.widget.EditText;
import android.widget.RatingBar;

import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by Braian Nunes on 8/16/2015.
 */
public class FormularioHelper {


    private final EditText nome;
    private final EditText endereco;
    private final EditText site;
    private final RatingBar nota;
    private final EditText telefone;

    public FormularioHelper(Formulario formulario) {
        nome = (EditText) formulario.findViewById(R.id.ed_nome);
        endereco = (EditText) formulario.findViewById(R.id.ed_endereco);
        site = (EditText) formulario.findViewById(R.id.ed_site);
        telefone = (EditText) formulario.findViewById(R.id.ed_telefone);
        nota = (RatingBar) formulario.findViewById(R.id.rating_nota);
    }

    public Aluno obterAlunoDoFormulario() {
        final Aluno aluno = new Aluno();
        aluno.setNome(nome.getText().toString());
        aluno.setEndereco(endereco.getText().toString());
        aluno.setSite(site.getText().toString());
        aluno.setTelefone(telefone.getText().toString());
        aluno.setNota(Double.valueOf(nota.getRating()));

        return aluno;
    }

    public void colocaAlunoNoFormulario(Aluno alunoParaSerAlterado) {
        Log.i("FormHelper", alunoParaSerAlterado.getNome());
        nome.setText(alunoParaSerAlterado.getNome());
        Log.i("FormHelper", alunoParaSerAlterado.getEndereco());
        endereco.setText(alunoParaSerAlterado.getEndereco());
        Log.i("FormHelper", alunoParaSerAlterado.getSite());
        site.setText(alunoParaSerAlterado.getSite());
        telefone.setText(alunoParaSerAlterado.getTelefone());
        nota.setRating(alunoParaSerAlterado.getNota().floatValue());
    }
}
