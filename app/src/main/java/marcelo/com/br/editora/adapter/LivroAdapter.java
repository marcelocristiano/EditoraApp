package marcelo.com.br.editora.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import java.util.List;

import marcelo.com.br.editora.R;
import marcelo.com.br.editora.interfaces.IOnClickRecycleView;
import marcelo.com.br.editora.model.Livro;
import marcelo.com.br.editora.model.Retrofit;
import marcelo.com.br.editora.util.Alerta;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Marcelo on 14/03/2017.
 */

public class LivroAdapter extends RecyclerView.Adapter<LivroAdapter.ViewHolder> {
    private List<Livro> listaLivro;
    private Context context;


    public LivroAdapter(List<Livro> listaLivro, Context context) {
        this.listaLivro = listaLivro;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Infla a view que será mostrada no RecycleView.
        LayoutInflater inflater = LayoutInflater.from(context);
        // Cria um view para receber a view inflada.
        View view = inflater.inflate(R.layout.activity_lista_livro, parent, false);
        // Retorna a view inflada.
        return new LivroAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // Recupera valores da lista de livros.
        final Livro livro = listaLivro.get(position);

        // Seta valores dos textviews da view inflada.
        holder.tvTitulo.setText(livro.getTitulo());
        holder.tvAutor.setText(livro.getAutor());
        holder.tvAnoLancamento.setText(String.valueOf(livro.getAnoLancamento()));

        // Seta o click no recycle view.
        holder.setItemClickListener(new IOnClickRecycleView() {
            @Override
            public void onClick(final View view, final int position, boolean isLongClick) {

                view.setOnClickListener(new View.OnClickListener() {

                    TextView tvTituloEdita, tvAutorEdita, tvAnoLancamentoEdita;
                    Button btnGrava, btnok, btnEdita;

                    @Override
                    public void onClick(View v) {

                        // Instancia um AlertDialog.
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

                        // Infla a view para mostrar junto ao AlertDialog.
                        final LayoutInflater inflater = LayoutInflater.from(context);

                        // Cria um view para receber a view inflada.
                        final View layoutTeste = inflater.inflate(R.layout.teste, null, false);

                        // Recupera os objetos da view inflada(TextView).
                        tvTituloEdita = (TextView) layoutTeste.findViewById(R.id.edt_titulo_edita);
                        tvAutorEdita = (TextView) layoutTeste.findViewById(R.id.edt_autor_edita);
                        tvAnoLancamentoEdita = (TextView) layoutTeste.findViewById(R.id.edt_ano_lancamento_edita);

                        // Recupera os objetos da view inflada (Button).
                        btnEdita = (Button) layoutTeste.findViewById(R.id.btn_edita);
                        btnGrava = (Button) layoutTeste.findViewById(R.id.btn_grava);
                        btnok = (Button) layoutTeste.findViewById(R.id.btn_ok);

                        // Desabilita os EditTexts.
                        tvTituloEdita.setEnabled(false);
                        tvTituloEdita.setFocusable(false);
                        tvAutorEdita.setEnabled(false);
                        tvAutorEdita.setFocusable(false);
                        tvAnoLancamentoEdita.setEnabled(false);
                        tvAnoLancamentoEdita.setFocusable(false);

                        // Desabilita os Buttons
                        btnGrava.setEnabled(false);

                        // Seta valores de item escolhido.
                        tvTituloEdita.setText(listaLivro.get(position).getTitulo());
                        tvAutorEdita.setText(listaLivro.get(position).getAutor());
                        tvAnoLancamentoEdita.setText(String.valueOf(listaLivro.get(position).getAnoLancamento()));

                        // Cria um AlertDialog.
                        final AlertDialog builder = alertDialog.create();
                        builder.setCancelable(false);
                        builder.setView(layoutTeste);

                        // Seta Click do botão OK.
                        btnok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                builder.dismiss();
                            }
                        });

                        // Seta Click do botão OK.
                        btnEdita.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Habilita botão gravar.
                                btnGrava.setEnabled(true);
                                // Altera a cor.
                                btnGrava.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

                                // Desabilitar o botão Ok.
                                btnok.setText("Cancelar");

                                // Habilita os EditTexts
                                tvTituloEdita.setEnabled(true);
                                tvTituloEdita.setFocusableInTouchMode(true);
                                tvAutorEdita.setEnabled(true);
                                tvAutorEdita.setFocusableInTouchMode(true);
                                tvAnoLancamentoEdita.setEnabled(true);
                                tvAnoLancamentoEdita.setFocusableInTouchMode(true);
                            }
                        });

                        // Seta o click do botão gravar.
                        btnGrava.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(final View v) {
                                Livro livro1 = new Livro();
                                livro1.setTitulo(tvTituloEdita.getText().toString());
                                livro1.setAutor(tvAutorEdita.getText().toString());
                                livro1.setAnoLancamento(Integer.parseInt(tvAnoLancamentoEdita.getText().toString()));

                                Retrofit retrofit = new Retrofit();
                                Call<Livro> call = retrofit.iApiLivro.updateLivro(livro1, listaLivro.get(position).getId_livro());
                                call.enqueue(new Callback<Livro>() {
                                    @Override
                                    public void onResponse(Call<Livro> call, Response<Livro> response) {

                                    }

                                    @Override
                                    public void onFailure(Call<Livro> call, Throwable t) {

                                    }
                                });
                                // Desabilita os EditTexts.
                                tvTituloEdita.setEnabled(false);
                                tvTituloEdita.setFocusable(false);
                                tvAutorEdita.setEnabled(false);
                                tvAutorEdita.setFocusable(false);
                                tvAnoLancamentoEdita.setEnabled(false);
                                tvAnoLancamentoEdita.setFocusable(false);

                                // Desabilita os Buttons
                                btnGrava.setEnabled(false);
                                // Altera a cor.
                                btnGrava.setTextColor(ContextCompat.getColor(context, R.color.preto));

                                /*Desabilita o botão Ok*/
                                btnok.setText("Ok");
                            }
                        });
                        builder.show();
                    }
                });
                view.setOnLongClickListener(new View.OnLongClickListener()

                {
                    @Override
                    public boolean onLongClick(final View v) {
                        // Instancia um objeto Retrofit.
                        Retrofit retrofit = new Retrofit();

                        // Cria um obejto chamda Call<Livro> pra receber os valores restornados do service.
                        Call<Livro> call = retrofit.iApiLivro.deleteLivro(listaLivro.get(position).getId_livro());

                        // Realiza uma chamada assincrona.
                        call.enqueue(new Callback<Livro>() {
                            @Override
                            public void onResponse(Call<Livro> call, Response<Livro> response) {

                                // Verifica se a resposta é um sucesso.
                                if (response.isSuccessful()) {

                                    // Cria um Snackbar.
                                    Snackbar.make(v, "Teste" + response.code(), Snackbar.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Livro> call, Throwable t) {
                                Log.e("Erro", "Erro" + t.getMessage());
                            }
                        });

                        return true;
                    }
                });
            }

        });

    }

    @Override
    public int getItemCount() {
        return listaLivro.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView tvTitulo, tvAutor, tvAnoLancamento;
        private IOnClickRecycleView iOnClickRecycleView;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitulo = (TextView) itemView.findViewById(R.id.tv_titulo);
            tvAutor = (TextView) itemView.findViewById(R.id.tv_autor);
            tvAnoLancamento = (TextView) itemView.findViewById(R.id.tv_ano_lancamento);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setItemClickListener(IOnClickRecycleView itemClickListener) {
            this.iOnClickRecycleView = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            iOnClickRecycleView.onClick(v, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            iOnClickRecycleView.onClick(v, getAdapterPosition(), true);
            return true;
        }
    }
}
