package bones.aito3d;

/**
 * Created by luisalfonsobejaranosanchez on 8/5/17.
 */


import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

interface Service {

    /*
    @FormUrlEncoded
    @POST("/")
    Call<ResponseBody> checkImage(@Field("name") String name, @Field("genre")
                                 String genre, @Field("age") String age, @Field("imageId") String imageId);
    */


    @GET("/predict")
    Call<ResponseBody> checkImage(@Body HashMap<String, String> user);


}
