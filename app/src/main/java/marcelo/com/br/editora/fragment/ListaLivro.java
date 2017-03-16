package marcelo.com.br.editora.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import marcelo.com.br.editora.R;
import marcelo.com.br.editora.adapter.LivroAdapter;
import marcelo.com.br.editora.model.Livro;
import marcelo.com.br.editora.model.Retrofit;
import marcelo.com.br.editora.util.Alerta;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaLivro extends Fragment {
    private RecyclerView rcvLivro;
    private RecyclerView.LayoutManager layoutManager;
    private List<Livro> listaDeLivro;
    private ProgressDialog progressDialog;

    public ListaLivro() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_lista_livro, container, false);

        // Recupera objeto recycleview da view.
        rcvLivro = (RecyclerView) view.findViewById(R.id.rcv_livro);
        // Instancia um LayoutManager;
        layoutManager = new LinearLayoutManager(getContext());
        // Seta o Layouut do recycleView.
        rcvLivro.setLayoutManager(layoutManager);
        // Instancia um objeto Retrofit.
        Retrofit retrofit = new Retrofit();
        // Inntancia um obejto ProgressDialog.
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Carregando...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Call<List<Livro>> call = retrofit.iApiLivro.selectAll();
        call.enqueue(new Callback<List<Livro>>() {
            @Override
            public void onResponse(Call<List<Livro>> call, Response<List<Livro>> response) {
                if (progressDialog.isShowing())
                    progressDialog.dismiss();

                if (response.isSuccessful()) {
                    // Recebe os valores de Livro.
                    listaDeLivro = response.body();
                    // Intancia um objeto do tipo LivroAdapter.
                    LivroAdapter adapter = new LivroAdapter(listaDeLivro, getContext());
                    // Sera o adapter do RecycleView.
                    rcvLivro.setAdapter(adapter);
                } else {
                    Log.e("Erro", "Erro" + response.code());
                    Alerta.showSnackbar(view, "'Não foi possivél carregar os dados.", Snackbar.LENGTH_SHORT);

                }
            }

            @Override
            public void onFailure(Call<List<Livro>> call, Throwable t) {
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                Log.e("Erro", "Erro" + t.getMessage());
            }
        });
        return view;
    }

}
