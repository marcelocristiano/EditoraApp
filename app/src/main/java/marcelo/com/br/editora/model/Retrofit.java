package marcelo.com.br.editora.model;

import marcelo.com.br.editora.interfaces.IApiLivro;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Marcelo on 14/03/2017.
 */

public class Retrofit  {
    static final String BASE_URL = "http://192.168.1.58:8080/Editora3/api/livro/";

   public retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public IApiLivro iApiLivro = retrofit.create(IApiLivro.class);
}
