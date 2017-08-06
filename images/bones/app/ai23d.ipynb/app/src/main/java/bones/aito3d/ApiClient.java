package bones.aito3d;

import retrofit2.Retrofit;


/**
 * Created by luisalfonsobejaranosanchez on 8/6/17.
 */

public class ApiClient {


    public static final String BASE_URL = "http://192.168.43.36:5000";
    private static Retrofit retrofit = null;
    public static int unique_id;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    //.addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
