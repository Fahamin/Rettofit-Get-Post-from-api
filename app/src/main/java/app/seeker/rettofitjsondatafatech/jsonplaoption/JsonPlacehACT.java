package app.seeker.rettofitjsondatafatech.jsonplaoption;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import app.seeker.rettofitjsondatafatech.R;
import app.seeker.rettofitjsondatafatech.marbel.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JsonPlacehACT extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_placeh);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView = findViewById(R.id.texId);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(JsonPlacehACT.this, MainActivity.class));

            }
        });

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("https://jsonplaceholder.typicode.com/").
                addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonApi api = retrofit.create(JsonApi.class);

        Call<List<JsonModel>> call = api.getAllDATA();

        call.enqueue(new Callback<List<JsonModel>>() {
            @Override
            public void onResponse(Call<List<JsonModel>> call, Response<List<JsonModel>> response) {
                if(response.isSuccessful())
                {
                    List<JsonModel> list = response.body();

                    for (JsonModel jsonModel : list)
                    {
                        String result  = "";

                        result += "Id" + jsonModel.getId()+"\n";
                        result += "userId" + jsonModel.getUserId()+"\n";
                        result += "title" + jsonModel.getTitle()+"\n\n";
                        result += "body"+jsonModel.getBody()+"\n\n";

                        textView.append(result);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<JsonModel>> call, Throwable t) {

            }
        });

    }

}
