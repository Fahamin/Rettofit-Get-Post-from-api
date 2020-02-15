package app.seeker.rettofitjsondatafatech.jsonplaoption;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.seeker.rettofitjsondatafatech.R;
import app.seeker.rettofitjsondatafatech.marbel.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JsonPlacehActivity extends AppCompatActivity {

    TextView textView;
    JsonApi api;

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
                startActivity(new Intent(JsonPlacehActivity.this, MainActivity.class));

            }
        });


        Gson gson = new GsonBuilder().serializeNulls().create();//for patch to show null title
       // addConverterFactory(GsonConverterFactory.create(gson))

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("https://jsonplaceholder.typicode.com/").
                addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(JsonApi.class);


        //   getPOSt();
        //  getCommet();

        //  createPost();
        //  createPost2();

        updatePost();

    }

    private void updatePost() {

        JsonModelPost post = new JsonModelPost(5, null, "new Text");
        Call<JsonModelPost> call = api.patchPost(4, post);
        // Call<JsonModelPost> call = api.putPost(4, post);

        call.enqueue(new Callback<JsonModelPost>() {
            @Override
            public void onResponse(Call<JsonModelPost> call, Response<JsonModelPost> response) {
                if (!response.isSuccessful()) {
                    textView.setText("code" + response.code());
                    Toast.makeText(JsonPlacehActivity.this, "post succfully", Toast.LENGTH_SHORT).show();
                }
                JsonModelPost post = response.body();
                String result = "";

                result += "Id" + post.getId() + "\n";
                result += "userId" + post.getUserId() + "\n";
                result += "title" + post.getTitle() + "\n\n";
                result += "body" + post.getBody() + "\n\n";

                textView.append(result);
            }

            @Override
            public void onFailure(Call<JsonModelPost> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

    private void createPost2() {

        Call<JsonModelPost> call = api.createPost2(12, "new title", "new body");


        call.enqueue(new Callback<JsonModelPost>() {
            @Override
            public void onResponse(Call<JsonModelPost> call, Response<JsonModelPost> response) {
                if (response.isSuccessful()) {
                    textView.setText("code" + response.code());
                    Toast.makeText(JsonPlacehActivity.this, "post succfully", Toast.LENGTH_SHORT).show();
                }
                JsonModelPost post = response.body();
                String result = "";

                result += "Id" + post.getId() + "\n";
                result += "userId" + post.getUserId() + "\n";
                result += "title" + post.getTitle() + "\n\n";
                result += "body" + post.getBody() + "\n\n";

                textView.append(result);
            }

            @Override
            public void onFailure(Call<JsonModelPost> call, Throwable t) {
                textView.setText("faild to post");

            }
        });
    }

    private void createPost() {

        JsonModelPost jsonModelPost = new JsonModelPost(111, "WOW", "THIS IS BODY");

        Call<JsonModelPost> call = api.createPost(jsonModelPost);


        call.enqueue(new Callback<JsonModelPost>() {
            @Override
            public void onResponse(Call<JsonModelPost> call, Response<JsonModelPost> response) {
                if (response.isSuccessful()) {
                    textView.setText("code" + response.code());
                    Toast.makeText(JsonPlacehActivity.this, "post succfully", Toast.LENGTH_SHORT).show();
                }
                JsonModelPost post = response.body();
                String result = "";

                result += "Id" + post.getId() + "\n";
                result += "userId" + post.getUserId() + "\n";
                result += "title" + post.getTitle() + "\n\n";
                result += "body" + post.getBody() + "\n\n";

                textView.append(result);
            }

            @Override
            public void onFailure(Call<JsonModelPost> call, Throwable t) {
                textView.setText("faild to post");

            }
        });

    }

    private void getCommet() {

        Call<List<JsonModelComment>> call = api.getAllComment("posts/3/comments");

        call.enqueue(new Callback<List<JsonModelComment>>() {
            @Override
            public void onResponse(Call<List<JsonModelComment>> call, Response<List<JsonModelComment>> response) {

                if (response.isSuccessful()) {
                    List<JsonModelComment> list = response.body();

                    for (JsonModelComment comment : list) {
                        String addString = "";
                        addString += comment.getId() + "\n\n";
                        addString += comment.getPostId() + "\n\n";
                        addString += comment.getName() + "\n\n";
                        addString += comment.getEmail() + "\n\n";

                        textView.append(addString);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<JsonModelComment>> call, Throwable t) {

            }
        });
    }

    private void getPOSt() {

        Map<String, String> param = new HashMap<>();
        param.put("id", "3");
        param.put("_sort", "desc");


        Call<List<JsonModelPost>> call = api.getAllDATA(param);

        call.enqueue(new Callback<List<JsonModelPost>>() {
            @Override
            public void onResponse(Call<List<JsonModelPost>> call, Response<List<JsonModelPost>> response) {
                if (response.isSuccessful()) {
                    List<JsonModelPost> list = response.body();

                    for (JsonModelPost jsonModel : list) {
                        String result = "";

                        result += "Id" + jsonModel.getId() + "\n";
                        result += "userId" + jsonModel.getUserId() + "\n";
                        result += "title" + jsonModel.getTitle() + "\n\n";
                        result += "body" + jsonModel.getBody() + "\n\n";

                        textView.append(result);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<JsonModelPost>> call, Throwable t) {

            }
        });
    }

}
