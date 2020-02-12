package app.seeker.rettofitjsondatafatech.jsonplaoption;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonApi {

   /* @GET("posts")
    Call<List<JsonModel>>getAllDATA();
    */

   /* @GET("posts")
    Call<List<JsonModel>>getAllDATA(@Query("userId") int userID);
*/
    @GET("posts")
    Call<List<JsonModel>>getAllDATA(
            @Query("userId") int userID,
            @Query("_sort") String sort,
            @Query("_order") String order
            );

    @GET("posts/{id}/comments")
    Call<List<CommentModel>>getAllComment(@Path("id") int postId);
}
