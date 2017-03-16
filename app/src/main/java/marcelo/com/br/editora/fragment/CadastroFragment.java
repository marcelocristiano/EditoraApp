package marcelo.com.br.editora.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.animation.ValueAnimatorCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import marcelo.com.br.editora.MainActivity;
import marcelo.com.br.editora.R;
import marcelo.com.br.editora.model.Livro;
import marcelo.com.br.editora.model.Retrofit;
import marcelo.com.br.editora.util.Alerta;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadastroFragment extends Fragment {
    private EditText edtTitulo;
    private EditText edtAutor;
    private EditText edtAnoLancamento;
    private Button btnGravraLivro;
    private Alerta alerta;


    public CadastroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_cadastro, container, false);

        // Instacia um objeto do tipo Alerta.
        alerta = new Alerta();

        // Recupera objetos da view
        edtTitulo = (EditText) view.findViewById(R.id.edt_titulo);
        edtAutor = (EditText) view.findViewById(R.id.edt_autor);
        edtAnoLancamento = (EditText) view.findViewById(R.id.edt_ano_lancamento);
        btnGravraLivro = (Button) view.findViewById(R.id.btn_grava_livro);

        // Seta Click do botão GravarLivro.
        btnGravraLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Armazena os valores digitados.
                String titulo = edtTitulo.getText().toString();
                String autor = edtAutor.getText().toString();
                String anoLancamento = edtAnoLancamento.getText().toString();

                // Verifica se os valores inseridos são nulos.
                if (titulo.equals("")) {
                    alerta.showSnackbar(view, "Campo Titulo obrigatorio", Snackbar.LENGTH_SHORT);
                }
                if (autor.equals("") || edtAutor == null) {
                    alerta.showSnackbar(view, "Campo Autor obrigatorio", Snackbar.LENGTH_SHORT);
                }
                if (anoLancamento.equals("") || edtAnoLancamento == null) {
                    alerta.showSnackbar(view, "Campo Ano Lançamento obrigatorio", Snackbar.LENGTH_SHORT);
                }

                // Cria um objeto de Livro.
                Livro livro = new Livro();
                // Seta valores de Livro.
                livro.setTitulo(titulo);
                livro.setAutor(autor);
                livro.setAnoLancamento(Integer.parseInt(anoLancamento));
                if (gravaLivro(livro) == true) {
                    alerta.showSnackbar(view, "Dados gravados com sucesso", Snackbar.LENGTH_SHORT);
                    limpaCampos();
                }

            }
        });

        return view;
    }

    private boolean gravaLivro(Livro livro) {
        // Intancia um Objeto Retrofit.
        Retrofit retrofit = new Retrofit();

        // Cria um chamda da API SERVICE.
        Call<Livro> call = retrofit.iApiLivro.insertLivro(livro);
        call.enqueue(new Callback<Livro>() {
            @Override
            public void onResponse(Call<Livro> call, Response<Livro> response) {
                if (response.isSuccessful()) {
                    limpaCampos();
                    Snackbar.make(getView(), "Dados gravados com sucesso", Snackbar.LENGTH_LONG).show();
                } else {
                    Log.e("Erro", "Erro" + response.code());
                }
            }

            @Override
            public void onFailure(Call<Livro> call, Throwable t) {
                Log.e("Erro", "Erro" + t.getMessage());
            }
        });

        return true;
    }

    private void limpaCampos() {
        edtTitulo.setText("");
        edtAutor.setText("");
        edtAnoLancamento.setText("");
    }

}
