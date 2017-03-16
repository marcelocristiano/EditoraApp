package marcelo.com.br.editora.interfaces;

import java.util.List;

import marcelo.com.br.editora.model.Livro;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Marcelo on 14/03/2017.
 */

public interface IApiLivro {

    @GET("listaLivros")
    Call<List<Livro>> selectAll();

    @POST("insertLivro")
    Call<Livro> insertLivro(@Body Livro livro);

    @GET("delete/{idLivro}")
    Call<Livro> deleteLivro(@Path("idLivro") int idLivro);

    @PUT("edita/{idLivro}")
    Call<Livro> updateLivro(@Body Livro livro, @Path("idLivro") int idLivro);

}
