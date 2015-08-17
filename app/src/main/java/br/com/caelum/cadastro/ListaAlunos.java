package br.com.caelum.cadastro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;

public class ListaAlunos extends AppCompatActivity {

    private ListView lista;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        lista = (ListView) findViewById(R.id.lista);
        registerForContextMenu(lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Aluno aluno = (Aluno) parent.getItemAtPosition(position);
                Log.i("ListaAluno", aluno.getNome() + " endereco: " + aluno.getEndereco());
                final Intent irParaFormulario = new Intent(ListaAlunos.this, Formulario.class);
                irParaFormulario.putExtra("alunoSelecionado", aluno);
                startActivity(irParaFormulario);
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                aluno = (Aluno) parent.getItemAtPosition(position);
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add("Ligar");
        menu.add("Enviar SMS");
        menu.add("Navegar no Site");
        menu.add("Ver no mapa");
        menu.add("Enviar E-mail");

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                final AlunoDAO alunoDAO = new AlunoDAO(ListaAlunos.this);
                alunoDAO.deletar(aluno);
                alunoDAO.close();
                carregaLista();
                Log.i("AGENDA", "Deletando o aluno: " + aluno.getId() + " Nome: " + aluno.getNome());
                return false;
            }
        });

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    private void carregaLista() {
        final AlunoDAO alunoDAO = new AlunoDAO(this);
        List<Aluno> listaAluno = alunoDAO.getLista();
        alunoDAO.close();

        final int layout = android.R.layout.simple_list_item_1;
        final ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, layout, listaAluno);
        lista.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_alunos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.novo:
                Intent irParaFormulario = new Intent(this, Formulario.class);
                startActivity(irParaFormulario);
                break;

            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
