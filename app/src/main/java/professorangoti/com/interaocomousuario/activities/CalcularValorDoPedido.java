package professorangoti.com.interaocomousuario.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import professorangoti.com.interaocomousuario.R;
import professorangoti.com.interaocomousuario.dominio.Preco;
import professorangoti.com.interaocomousuario.services.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalcularValorDoPedido extends AppCompatActivity {
    String pedido;
    List<Preco> lista = new ArrayList<Preco>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_valor_do_pedido);
        Intent intent = getIntent();
        pedido = intent.getStringExtra("pedido");
        consultaServidor();
    }

    private void consultaServidor() {
        RetrofitService.getServico().getPrecos().enqueue(new Callback<List<Preco>>() {
            @Override
            public void onResponse(Call<List<Preco>> call, Response<List<Preco>> response) {
                int statusCode = response.code();
                lista = response.body();
                fechaConta();
            }

            @Override
            public void onFailure(Call<List<Preco>> call, Throwable t) {
                Toast.makeText(CalcularValorDoPedido.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fechaConta() {
        if (pedido.equals(lista.get(0).getProduto())) {
            ((TextView) findViewById(R.id.textView)).setText(lista.get(0).getValor() + "");
        }
        if (pedido.equals(lista.get(1).getProduto())) {
            ((TextView) findViewById(R.id.textView)).setText(lista.get(1).getValor() + "");
        }
        if (pedido.equals(lista.get(2).getProduto())) {
            ((TextView) findViewById(R.id.textView)).setText(lista.get(2).getValor() + "");
        }
    }
}
