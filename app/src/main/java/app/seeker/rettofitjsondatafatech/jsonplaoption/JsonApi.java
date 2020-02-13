package app.seeker.rettofitjsondatafatech.jsonplaoption;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonApi {


// basee url : https://jsonplaceholder.typicode.com/


   /* @GET("posts")
    Call<List<JsonModelPost>>getAllDATA();
    */

   /* @GET("posts")
    Call<List<JsonModelPost>>getAllDATA(@Query("userId") int userID);
*/

  @GET("posts")
    Call<List<JsonModelPost>>getAllDATA(@QueryMap Map<String ,String> parameters);

   /* @GET("posts")
    Call<List<JsonModelPost>>getAllDATA(
            @Query("userId") int userID,
            @Query("_sort") String sort,
            @Query("_order") String order
            );*/

  /*  @GET("posts/{id}/comments")
    Call<List<JsonModelComment>>getAllComment(@Path("id") int postId);*/

    @GET
  Call<List<JsonModelComment>>getAllComment(@Url String url);
}
