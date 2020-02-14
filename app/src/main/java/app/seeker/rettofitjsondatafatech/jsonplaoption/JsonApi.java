package app.seeker.rettofitjsondatafatech.jsonplaoption;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonApi {


// base url : https://jsonplaceholder.typicode.com/


   /* @GET("posts")
    Call<List<JsonModelPost>>getAllDATA();
    */

   /* @GET("posts")
    Call<List<JsonModelPost>>getAllDATA(@Query("userId") int userID);
*/

    @GET("posts")
    Call<List<JsonModelPost>> getAllDATA(@QueryMap Map<String, String> parameters);

   /* @GET("posts")
    Call<List<JsonModelPost>>getAllDATA(
            @Query("userId") int userID,
            @Query("_sort") String sort,
            @Query("_order") String order
            );*/

  /*  @GET("posts/{id}/comments")
    Call<List<JsonModelComment>>getAllComment(@Path("id") int postId);*/

    @GET
    Call<List<JsonModelComment>> getAllComment(@Url String url);


    // here is post to url option

    @POST("posts")
    Call<JsonModelPost> createPost(@Body JsonModelPost jsonModelPost);

    @FormUrlEncoded
    @POST("posts")
    Call<JsonModelPost> createPost2(
            @Field("userId") int userID,
            @Field("title")String title,
            @Field("body") String text
    );
}
