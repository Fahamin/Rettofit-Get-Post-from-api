package app.seeker.rettofitjsondatafatech.marbel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import app.seeker.rettofitjsondatafatech.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.recycleView);
        //calling the method to display the heroes
        getHeroes();
    }

    private void getHeroes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);//api interface

        Call<List<Hero>> call = api.getHeroes();

        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                List<Hero> heroList = response.body();

                JsonAdapter adapter = new JsonAdapter(MainActivity.this, heroList, listView);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                listView.setLayoutManager(layoutManager);
                listView.setAdapter(adapter);

              /*  //Creating an String array for the ListView
                String[] heroes = new String[heroList.size()];
                StringBuffer buffer =new StringBuffer();
                //looping through all the heroes and inserting the names inside the string array
                for (int i = 0; i < heroList.size(); i++) {
                    *//*heroes[i] = heroList.get(i).getName();
                    heroes[i]=heroList.get(i).getCreatedby();
                    heroes[i]=heroList.get(i).getImageurl();
                    heroes[i]=heroList.get(i).getFirstappearance();*//*
                    heroes[i]= String.valueOf(buffer.append(heroList.get(i).getImageurl()).append(" \n")
                            .append(heroList.get(i).getRealname()).append("\n\n"));

                }

                //displaying the string array into listview
                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, heroes));
*/
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}