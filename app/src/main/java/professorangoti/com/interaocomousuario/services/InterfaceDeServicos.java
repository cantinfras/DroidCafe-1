package professorangoti.com.interaocomousuario.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import professorangoti.com.interaocomousuario.dominio.Preco;

public interface InterfaceDeServicos {
    @GET("/precos")
    Call<List<Preco>> getPrecos();

}

